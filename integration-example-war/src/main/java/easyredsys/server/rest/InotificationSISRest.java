package easyredsys.server.rest;

import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.core.MessageOrderCESResponse;
import easyredsys.client.core.NotificationCES;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/InotificacionSIS")
public class InotificacionSISRest {
    private static final Logger LOGGER = Logger.getLogger(InotificacionSISRest.class.getName());

    @Inject
    private EasyRedsysConfiguration appConfig;

    @POST
    public Response notificar(
            @FormParam("Ds_SignatureVersion") String ds_SignatureVersion,
            @FormParam("Ds_MerchantParameters") String ds_MerchantParameters,
            @FormParam("Ds_Signature") String ds_Signature,
            @Context HttpServletRequest request) {

        LOGGER.log(Level.INFO, "Notificación del banco recibida");

        LOGGER.log(Level.FINEST, "ds_SignatureVersion: " + ds_SignatureVersion);
        LOGGER.log(Level.FINEST, "ds_MerchantParameters: " + ds_MerchantParameters);
        LOGGER.log(Level.FINEST, "ds_Signature: " + ds_Signature);

        if (!appConfig.getSecurityPolicy().isValidIp(request.getRemoteAddr())) {
            LOGGER.log(Level.WARNING, "La notificación se ha recibido desde una IP no permitida");

            return Response.status(400).build();
        }

        String password;
        if (appConfig == null) {
            LOGGER.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            LOGGER.log(Level.WARNING, "Usando password por defecto de la pasarela de test: 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'");
            password = "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
        } else {
            password = appConfig.getSecretKey();
        }

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(ds_SignatureVersion, ds_Signature, ds_MerchantParameters);

        LOGGER.log(Level.FINEST, "Notificación recibida: " + messageOrderCESResponse.getOperationCES());

        if (!messageOrderCESResponse.isValid(appConfig.getSecretKey())) {
            LOGGER.log(Level.WARNING, "Notificación para el pedido " + messageOrderCESResponse.getOperationCES().getDs_Order() + " recibida erróneamente. La firma no es válida");

            return Response.status(400).build();
        }

        LOGGER.info("Notificación válida recibida para la order: " + messageOrderCESResponse.getOperationCES().getDs_Order());

        if (appConfig == null) {
            LOGGER.log(Level.WARNING, "El bean con los datos de la pasarela no se ha inyectado. Debes crear una clase que implemente la interface AppConfig");
            LOGGER.log(Level.WARNING, "No hay nada que hacer con la notificación recibida");
        } else {
            notifyResponse(messageOrderCESResponse.getOperationCES());
        }

        return Response.status(200).build();
    }

    protected void notifyResponse(NotificationCES operationCES) {
        LOGGER.log(Level.WARNING, "Message received. You should implement your own handler");
    }


}