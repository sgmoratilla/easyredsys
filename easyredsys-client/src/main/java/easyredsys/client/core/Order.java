package easyredsys.client.core;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sis.redsys.api.ApiMacSha256;

import javax.xml.bind.annotation.XmlElement;

public abstract class Order {
    public final static String DS_MERCHANT_AMOUNT = "DS_MERCHANT_AMOUNT";
    public final static String DS_MERCHANT_ORDER = "DS_MERCHANT_ORDER";
    public final static String DS_MERCHANT_MERCHANTCODE = "DS_MERCHANT_MERCHANTCODE";
    public final static String DS_MERCHANT_TERMINAL = "DS_MERCHANT_TERMINAL";
    public final static String DS_MERCHANT_CURRENCY = "DS_MERCHANT_CURRENCY";
    public final static String DS_MERCHANT_TRANSACTIONTYPE = "DS_MERCHANT_TRANSACTIONTYPE";
    public final static String DS_MERCHANT_PRODUCTDESCRIPTION = "DS_MERCHANT_PRODUCTDESCRIPTION";
    public final static String DS_MERCHANT_TITULAR = "DS_MERCHANT_TITULAR";
    public final static String DS_MERCHANT_MERCHANTDATA = "DS_MERCHANT_MERCHANTDATA";


    protected final static String ds_SignatureVersion = "HMAC_SHA256_V1";

    protected final ApiMacSha256 apiMacSha256 = new ApiMacSha256();

    public Order() {
        apiMacSha256.setParameter(DS_MERCHANT_AMOUNT, "");
        apiMacSha256.setParameter(DS_MERCHANT_ORDER, "");
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTCODE, "");
        apiMacSha256.setParameter(DS_MERCHANT_TERMINAL, "");
        apiMacSha256.setParameter(DS_MERCHANT_CURRENCY, "");
        apiMacSha256.setParameter(DS_MERCHANT_TRANSACTIONTYPE, "");
        apiMacSha256.setParameter(DS_MERCHANT_PRODUCTDESCRIPTION, "");
        apiMacSha256.setParameter(DS_MERCHANT_TITULAR, "");
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTDATA, "");
    }

    public String getDs_SignatureVersion() {
        return ds_SignatureVersion;
    }

    public String getDs_merchant_amount() {
        return apiMacSha256.getParameter(DS_MERCHANT_AMOUNT);
}

    public void setDs_merchant_amount(@NonNull long ds_merchant_amount) {
        apiMacSha256.setParameter(DS_MERCHANT_AMOUNT, Long.toString(ds_merchant_amount));
    }

    public String getDs_merchant_order() {
        return apiMacSha256.getParameter(DS_MERCHANT_ORDER);
    }

    public void setDs_merchant_order(@NonNull String ds_merchant_order) {
        apiMacSha256.setParameter(DS_MERCHANT_ORDER, ds_merchant_order);
    }

    public String getDs_merchant_merchantcode() {
        return apiMacSha256.getParameter(DS_MERCHANT_MERCHANTCODE);
    }

    public void setDs_merchant_merchantcode(@NonNull String ds_merchant_merchantcode) {
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTCODE, ds_merchant_merchantcode);
    }

    public String getDs_merchant_terminal() {
        return apiMacSha256.getParameter(DS_MERCHANT_TERMINAL);
    }

    public void setDs_merchant_terminal(@NonNull String ds_merchant_terminal) {
        apiMacSha256.setParameter(DS_MERCHANT_TERMINAL, ds_merchant_terminal);
    }

    public String getDs_merchant_currency() {
        return apiMacSha256.getParameter(DS_MERCHANT_CURRENCY);
    }

    public void setDs_merchant_currency(long ds_merchant_currency) {
        apiMacSha256.setParameter(DS_MERCHANT_CURRENCY, Long.toString(ds_merchant_currency));
    }

    public String getDs_merchant_transactiontype() {
        return apiMacSha256.getParameter(DS_MERCHANT_TRANSACTIONTYPE);
    }

    public void setDs_merchant_transactiontype(@NonNull String ds_merchant_transactiontype) {
        apiMacSha256.setParameter(DS_MERCHANT_TRANSACTIONTYPE, ds_merchant_transactiontype);
    }

    public String getDs_merchant_productdescription() {
        return apiMacSha256.getParameter(DS_MERCHANT_PRODUCTDESCRIPTION);
    }

    public void setDs_merchant_productdescription(@NonNull String ds_merchant_productdescription) {
        apiMacSha256.setParameter(DS_MERCHANT_PRODUCTDESCRIPTION, ds_merchant_productdescription);
    }

    public String getDs_merchant_titular() {
        return apiMacSha256.getParameter(DS_MERCHANT_TITULAR);
    }

    public void setDs_merchant_titular(@NonNull String ds_merchant_titular) {
        apiMacSha256.setParameter(DS_MERCHANT_TITULAR, ds_merchant_titular);
    }

    public String getDs_merchant_merchantdata() {
        return apiMacSha256.getParameter(DS_MERCHANT_MERCHANTDATA);
    }

    public void setDs_merchant_merchantdata(@NonNull String ds_merchant_merchantdata) {
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTDATA, ds_merchant_merchantdata);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(DS_MERCHANT_AMOUNT).append(":");
        sb.append(getDs_merchant_amount());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_ORDER).append(":");
        sb.append(getDs_merchant_order());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_MERCHANTCODE).append(":");
        sb.append(getDs_merchant_merchantcode());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_TERMINAL).append(":");
        sb.append(getDs_merchant_terminal());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_CURRENCY).append(":");
        sb.append(getDs_merchant_currency());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_TRANSACTIONTYPE).append(":");
        sb.append(getDs_merchant_transactiontype());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_PRODUCTDESCRIPTION).append(":");
        sb.append(getDs_merchant_productdescription());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_TITULAR).append(":");
        sb.append(getDs_merchant_titular());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_MERCHANTDATA).append(":");
        sb.append(getDs_merchant_merchantdata());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object instanceof Order) {
            Order order = (Order)object;

            if (order.getDs_merchant_order() !=null && order.getDs_merchant_order().equals(this.getDs_merchant_order())) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    public void assertIsSet() {
        assertHasText(DS_MERCHANT_AMOUNT);
        assertHasText(DS_MERCHANT_ORDER);
        assertHasText(DS_MERCHANT_MERCHANTCODE);
        assertHasText(DS_MERCHANT_TERMINAL);
        assertHasText(DS_MERCHANT_CURRENCY);
        assertHasText(DS_MERCHANT_TRANSACTIONTYPE);
        assertHasText(DS_MERCHANT_PRODUCTDESCRIPTION);
        assertHasText(DS_MERCHANT_TITULAR);

        // This is optional:
        //assertHasText(DS_MERCHANT_MERCHANTDATA);
    }

    protected void assertHasText(String propertyName) {
        Assert.hasText(apiMacSha256.getParameter(propertyName), propertyName + " must be set");

    }
}
