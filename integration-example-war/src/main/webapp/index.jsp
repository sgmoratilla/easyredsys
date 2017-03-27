<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="easyredsys.client.core.MessageOrderCESRequest" %>
<%@ page import="easyredsys.client.core.OrderCES" %>
<%@ page import="easyredsys.client.util.Currency" %>
<%@ page import="easyredsys.client.util.Language" %>
<%@ page import="easyredsys.client.util.PaymentMethod" %>
<%@ page import="easyredsys.client.util.TransactionType" %>
<%@ page import="easyredsys.example.AppConfigImpl" %>
<%@ page import="java.util.Random" %>

<%
    OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
            .transactionType(TransactionType.AUTORIZACION)
            .currency(Currency.EUR)
            .consumerLanguage(Language.SPANISH)
            .order(String.valueOf(getRandomOrder()))
            .amount(1000) //Equivalente a 10.00€
            .productDescription("Product description")
            .payMethods(PaymentMethod.TARJETA)
            .urlOk("https://easyredsys.miguelangeljulvez.com/easyredsys/response-ces-ok.jsp")
            .urlKo("https://easyredsys.miguelangeljulvez.com/easyredsys/response-ces-error.jsp")
            .urlNotification("http://easyredsys.miguelangeljulvez.com/easyredsys/rest/InotificacionSIS") //Preferiblemente usando https. Redsys no es compatible con los certifiados de StarSSL, que es el que uso, por lo que fallaría al enviarse la notificación
            .build();

    MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest.Builder(AppConfigImpl.class)
            .withOrder(orderCES)
            .build();

<!DOCTYPE html>
<html>
<head>
    <title>Easyredsys</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body class="text-center">
    <h1>Easyredsys TEST</h1>
    <p>&nbsp;</p>
    <fieldset>
        <legend>Compra Comercio Electrónico Seguro (CES)<br />Los datos bancarios los pide el banco</legend>
    <p>
        Tarjeta: 4548812049400004<br />
        Caducidad: 12/20<br />
        CVV: 123<br />
    </p>

    <form action="<%=messageOrderCESRequest.getRedsysUrl()%>" method="post">
        <input name="Ds_SignatureVersion" value="<%=messageOrderCESRequest.getDs_SignatureVersion()%>" type="hidden"/>
        <input name="Ds_MerchantParameters" value="<%=messageOrderCESRequest.getDs_MerchantParameters()%>" type="hidden"/>
        <input name="Ds_Signature" value="<%=messageOrderCESRequest.getDs_Signature()%>" type="hidden"/>
        <input type="submit" value="Comprar"/>
    </form>
    </fieldset>
</body>

</html>

<%!
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
%>