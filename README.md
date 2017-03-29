# EASY REDSYS: CES + REST #

This library is a simplified version of https://github.com/majulvez/easyredsys/ which only accepts CES requests
and REST notifications.

Software Requirements:
- Java 1.8
- Gradle (to compile this library)


Usage:

Add this library as dependency:

<dependency>
  <group>easyredsys</group>
  <name>easyredsys-client</name>
  <version>1.0.0</version>
</dependency>

CES payments (users are redirected to the bank environment in a browser):

1- Create your Order:

```
EasyRedsysConfiguration config = new ...
OrderCES orderCES = new OrderCES.Builder(config)
                        .transactionType(TransactionType.AUTORIZACION)
                        .currency(Currency.EUR)
                        .consumerLanguage(Language.SPANISH)
                        .order("<Unique id of the transaction>")
                        .amount(<The amount to charge in cents>)
                        .productDescription("Product description")
                        .paymentMethods(PaymentMethod.CARD)
                        .urlOk(<The URL where the user is redirect when the payment is done>)
                        .urlKo(<The URL where the user is redirect when the payment cannot be done>)
                        .urlNotification(<Your URL to receive the notifications from Redsys>)
                        .build();

MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest(orderCES);
```

2-Make a POST request to the Redsys URL. This is typically done with a web form (using Thymeleaf in this example):

```
    <form th:action="${configuration.redsysUrl}" method="post">
        <input name="Ds_SignatureVersion" th:value="${messageOrderCESRequest.ds_SignatureVersion}" type="hidden"/>
        <input name="Ds_MerchantParameters" th:value="${messageOrderCESRequest.ds_MerchantParameters}" type="hidden"/>
        <input name="Ds_Signature" th:value="${messageOrderCESRequest.getDs_Signature(configuration.secretKey)}" type="hidden"/>
        <input type="submit" value="Comprar"/>
    </form>
```
    
3- You must implement the callback for the URL that you have set in .urlNotification()


## Notification ##

Notifications are received as a POST form in the URL specified under .urlNotification().