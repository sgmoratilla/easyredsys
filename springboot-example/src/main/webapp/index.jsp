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

    <form action="<%=configuration.getRedsysUrl()%>" method="post">
        <input name="Ds_SignatureVersion" value="<%=messageOrderCESRequest.getDs_SignatureVersion()%>" type="hidden"/>
        <input name="Ds_MerchantParameters" value="<%=messageOrderCESRequest.getDs_MerchantParameters()%>" type="hidden"/>
        <input name="Ds_Signature" value="<%=messageOrderCESRequest.getDs_Signature()%>" type="hidden"/>
        <input type="submit" value="Comprar"/>
    </form>
    </fieldset>
</body>

</html>