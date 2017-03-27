package easyredsys.server.rest;

import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.core.MessageOrderCESRequest;
import easyredsys.client.core.MessageOrderCESResponse;
import easyredsys.client.core.NotificationCES;
import easyredsys.client.core.OrderCES;
import easyredsys.client.util.*;
import easyredsys.security.SecurityPolicy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class EasyRedsysController {
    private final Logger LOGGER = Logger.getLogger(EasyRedsysController.class.getName());

    // It shouldn't be here:
    private EasyRedsysConfiguration configuration = new TestEasyRedsysConfiguration();


    protected class TestEasyRedsysConfiguration implements EasyRedsysConfiguration {
        @Override
        public String getMerchantCode() {
            return "061978060";
        }

        @Override
        public String getTerminal() {
            return "001";
        }

        @Override
        public String getSecretKey() {
            return "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
        }

        @Override
        public String getRedsysUrl() {
            // FIXME: Don't use hardcoded URLs.
            return RedsysAddresses.getRedirectURL("test");
        }

        @Override
        public SecurityPolicy getSecurityPolicy() {
            return new SecurityPolicy();
        }
    }

    @RequestMapping(path = "/")
    public String index(Model model) throws Exception {


        OrderCES orderCES = new OrderCES.Builder(configuration)
                .transactionType(TransactionType.AUTORIZACION)
                .currency(Currency.EUR)
                .consumerLanguage(Language.SPANISH)
                .order(String.valueOf(getRandomOrder()))
                .amount(1000) //Equivalente a 10.00€
                .productDescription("Product description")
                .payMethods(PaymentMethod.TARJETA)
                .urlOk("http://localhost:8080/response-ces-ok.jsp")
                .urlKo("http://localhost:8080/response-ces-error.jsp")
                .urlNotification("http://localhost:8080/rest/InotificacionSIS") //Preferiblemente usando https. Redsys no es compatible con los certifiados de StarSSL, que es el que uso, por lo que fallaría al enviarse la notificación
                .build();

        MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest(orderCES);


        model.addAttribute("configuration", configuration);
        model.addAttribute("messageOrderCESRequest", messageOrderCESRequest);

        return "index";
    }

    @PostMapping(path = "/rest/InotificacionSIS")
    public ResponseEntity index(@ModelAttribute InotificationSISForm model, HttpServletRequest request) throws Exception {
        String ds_SignatureVersion = model.ds_SignatureVersion;
        String ds_MerchantParameters = model.ds_MerchantParameters;
        String ds_Signature = model.ds_Signature;


        LOGGER.log(Level.INFO, "Notificación del banco recibida");

        LOGGER.log(Level.FINEST, "ds_SignatureVersion: " + ds_SignatureVersion);
        LOGGER.log(Level.FINEST, "ds_MerchantParameters: " + ds_MerchantParameters);
        LOGGER.log(Level.FINEST, "ds_Signature: " + ds_Signature);

        if (!configuration.getSecurityPolicy().isValidIp(request.getRemoteAddr())) {
            LOGGER.log(Level.WARNING, "La notificación se ha recibido desde una IP no permitida");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        MessageOrderCESResponse messageOrderCESResponse = new MessageOrderCESResponse(ds_SignatureVersion, ds_Signature, ds_MerchantParameters);

        LOGGER.log(Level.FINEST, "Notificación recibida: " + messageOrderCESResponse.getOperationCES());

        if (!messageOrderCESResponse.isValid(configuration.getSecretKey())) {
            LOGGER.log(Level.WARNING, "Notificación para el pedido " + messageOrderCESResponse.getOperationCES().getDs_Order() + " recibida erróneamente. La firma no es válida");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Notificación válida recibida para la order: " + messageOrderCESResponse.getOperationCES().getDs_Order());

        notifyResponse(messageOrderCESResponse.getOperationCES());

        return new ResponseEntity(HttpStatus.OK);
    }

    protected void notifyResponse(NotificationCES operationCES) {
        LOGGER.log(Level.WARNING, "Message received. You should implement your own handler");
    }

    protected class InotificationSISForm {
        public String ds_SignatureVersion;
        public String ds_MerchantParameters;
        public String ds_Signature;
    }

    private String getRandomOrder() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER");
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}
