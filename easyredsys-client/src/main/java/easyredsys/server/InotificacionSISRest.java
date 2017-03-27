package easyredsys.server;


import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.core.MessageOrderCESResponse;
import easyredsys.client.core.NotificationCES;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InotificacionSISRest {
    private templates final Logger LOGGER = Logger.getLogger(InotificacionSISRest.class.getName());

    private EasyRedsysConfiguration easyRedsysConfiguration;

    public int notificar(
            String ds_SignatureVersion, // @FormParam("Ds_SignatureVersion")
            String ds_MerchantParameters, // @FormParam("Ds_MerchantParameters")
            String ds_Signature) // @FormParam("Ds_Signature"))
            {

        LOGGER.log(Level.INFO, "Notificación del banco recibida");

        LOGGER.log(Level.FINEST, "ds_SignatureVersion: " + ds_SignatureVersion);
        LOGGER.log(Level.FINEST, "ds_MerchantParameters: " + ds_MerchantParameters);
        LOGGER.log(Level.FINEST, "ds_Signature: " + ds_Signature);

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(ds_SignatureVersion, ds_Signature, ds_MerchantParameters);

        LOGGER.log(Level.FINEST, "Notificación recibida: " + messageOrderCESResponse.getOperationCES());

        if (!messageOrderCESResponse.isValid(easyRedsysConfiguration.getSecretKey())) {
            LOGGER.log(Level.WARNING, "Notificación para el pedido " + messageOrderCESResponse.getOperationCES().getDs_Order() + " recibida erróneamente. La firma no es válida");

            return 400;
        }

        LOGGER.info("Notificación válida recibida para la order: " + messageOrderCESResponse.getOperationCES().getDs_Order());

        notifyResponse(messageOrderCESResponse.getOperationCES());

        return 200;
    }

    private void notifyResponse(NotificationCES operationCES) {
        LOGGER.log(Level.WARNING, "Message received. You should implement your own handler");
    }
}
