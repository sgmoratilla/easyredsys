package easyredsys.server.rest;

import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.core.MessageOrderCESRequest;
import easyredsys.client.core.OrderCES;
import easyredsys.client.util.*;
import easyredsys.security.SecurityPolicy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import java.util.Optional;
import java.util.Random;

@Controller
public class EasyRedsysController {

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
        EasyRedsysConfiguration configuration = new TestEasyRedsysConfiguration();


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
    public String index(@ModelAttribute InotificationSISForm model) throws Exception {

    }

    protected   class InotificationSISForm {
        public String ds_SignatureVersion;
        public String ds_MerchantParameters;
        public String ds_Signature;
    }

    private String getRandomOrder() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}
