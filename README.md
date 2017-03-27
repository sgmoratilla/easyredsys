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

2-Make a POST request to the Redsys URL. This is typically done with a web form:

```
<form action="<%=messageOrderCESRequest.getRedsysUrl()%>" method="post">
    <input name="Ds_SignatureVersion" value="<%=messageOrderCESRequest.getDs_SignatureVersion()%>" type="hidden"/>
    <input name="Ds_MerchantParameters" value="<%=messageOrderCESRequest.getDs_MerchantParameters()%>" type="hidden"/>
    <input name="Ds_Signature" value="<%=messageOrderCESRequest.getDs_Signature()%>" type="hidden"/>
    <input type="submit" value="Submit"/>
</form>
```
    
3- You must implement the callback for the URL that you have set in .urlNotification()


## Notification ##

```
<dependency>
  <group>com.miguelangeljulvez.easyredsys</group>
  <name>easyredsys-server</name>
  <version>1.0.0</version>
</dependency>
```

Las notificaciones se reciben en el método saveNotificacion() de la interfaz AppConfig que has implementado previamente.

Las notificaciones que llegan a ese método han pasado todas las verificaciones y controles de seguridad.

Para publicar los diferentes servicios, deberás copiar el contenido de los ficheros web.xml, beans.xml y server-config.wsdd del submódulo "integration-example-war" al directorio WEB-INF de tu aplicación.

### Notificación ON-LINE: síncrona y asíncrona ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/rest/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/rest/InotificacionSIS")
   .build()
```
### Notificación ON-LINE: SOAP sin wsdl y con wsdl ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/axis/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/axis/InotificacionSIS")
   .build()
```
### Notificación ON-LINE; SOAP literal (recomendada) ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/literal/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/literal/InotificacionSIS")
   .build()
```