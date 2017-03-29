package easyredsys.client.core;


import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.util.Currency;
import easyredsys.client.util.Language;
import easyredsys.client.util.RedsysPaymentMethod;
import easyredsys.client.util.TransactionType;
import org.checkerframework.checker.nullness.qual.NonNull;

/*
** Orden de compra usando compra CES sin usar servicios web
 */
public final class OrderCES extends Order {
    public final static String DS_MERCHANT_MERCHANTURL = "DS_MERCHANT_MERCHANTURL";
    public final static String DS_MERCHANT_URLOK = "DS_MERCHANT_URLOK";
    public final static String DS_MERCHANT_URLKO = "DS_MERCHANT_URLKO";
    public final static String DS_MERCHANT_NAME = "DS_MERCHANT_NAME";
    public final static String DS_CONSUMER_LANGUAGE = "DS_CONSUMER_LANGUAGE";
    public final static String DS_MERCHANT_PAYMETHODS = "DS_MERCHANT_PAYMETHODS";



    protected OrderCES() {
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTURL, "");
        apiMacSha256.setParameter(DS_MERCHANT_URLOK, "");
        apiMacSha256.setParameter(DS_MERCHANT_URLKO, "");
        apiMacSha256.setParameter(DS_MERCHANT_NAME, "");
        apiMacSha256.setParameter(DS_CONSUMER_LANGUAGE, "");
        apiMacSha256.setParameter(DS_MERCHANT_PAYMETHODS, "");
    }

    public String getDs_merchant_merchantURL() {
        return apiMacSha256.getParameter(DS_MERCHANT_MERCHANTURL);
    }

    public void setDs_merchant_merchantURL(String ds_merchant_merchantURL) {
        apiMacSha256.setParameter(DS_MERCHANT_MERCHANTURL, ds_merchant_merchantURL);
    }

    public String getDs_merchant_UrlOK() {
        return apiMacSha256.getParameter(DS_MERCHANT_URLOK);
    }

    public void setDs_merchant_UrlOK(String ds_merchant_UrlOK) {
        apiMacSha256.setParameter(DS_MERCHANT_URLOK, ds_merchant_UrlOK);
    }

    public String getDs_merchant_UrlKO() {
        return apiMacSha256.getParameter(DS_MERCHANT_URLKO);
    }

    public void setDs_merchant_UrlKO(String ds_merchant_UrlKO) {
        apiMacSha256.setParameter("DS_MERCHANT_URLKO", ds_merchant_UrlKO);
    }

    public String getDs_merchant_merchantName() {
        return apiMacSha256.getParameter(DS_MERCHANT_NAME);
    }

    public void setDs_merchant_merchantName(String ds_merchant_merchantName) {
        apiMacSha256.setParameter(DS_MERCHANT_NAME, ds_merchant_merchantName);
    }

    public int getDs_merchant_consumerLanguage() {
        try {
            return Integer.valueOf(apiMacSha256.getParameter(DS_CONSUMER_LANGUAGE));
        } catch (NumberFormatException nef) {
            throw new IllegalStateException(nef);
        }
    }

    public void setDs_merchant_consumerLanguage(int ds_merchant_consumerLanguage) {
        apiMacSha256.setParameter(DS_CONSUMER_LANGUAGE, String.valueOf(ds_merchant_consumerLanguage));
    }

    public String getDs_merchant_paymethods() {
        return apiMacSha256.getParameter(DS_MERCHANT_PAYMETHODS);
    }

    public void setDs_merchant_paymethods(String ds_merchant_paymethods) {
        apiMacSha256.setParameter(DS_MERCHANT_PAYMETHODS, ds_merchant_paymethods);
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());

        sb.append(DS_MERCHANT_MERCHANTURL).append(":");
        sb.append(getDs_merchant_merchantURL());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_URLOK).append(":");
        sb.append(getDs_merchant_UrlOK());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_URLKO).append(":");
        sb.append(getDs_merchant_UrlKO());
        sb.append(System.lineSeparator());
        sb.append(DS_MERCHANT_NAME).append(":");
        sb.append(getDs_merchant_merchantName());
        sb.append(System.lineSeparator());
        sb.append(DS_CONSUMER_LANGUAGE).append(":");
        sb.append(getDs_merchant_consumerLanguage());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public void assertIsSet() {
        super.assertIsSet();

        // This can be optional. FIXME: make optional depending on the configuration.
        //assertHasText(DS_MERCHANT_MERCHANTURL);

        assertHasText(DS_MERCHANT_URLOK);
        assertHasText(DS_MERCHANT_URLKO);
        assertHasText(DS_MERCHANT_NAME);
        assertHasText(DS_CONSUMER_LANGUAGE);
        assertHasText(DS_MERCHANT_PAYMETHODS);
    }

    public static class Builder {

        private String merchantCode;
        private String terminal;
        private String transactionType;
        private long currency;
        private int consumerLanguage;
        private String order;
        private long amount;
        private String urlOk;
        private String urlKo;
        private String urlNotification;
        private String productDescription;
        private String payMethods;
        private String titular;
        private String merchantName;

        public Builder(@NonNull EasyRedsysConfiguration easyRedsysConfiguration) {
            this.merchantCode = easyRedsysConfiguration.getMerchantCode();
            this.terminal = easyRedsysConfiguration.getTerminal();
        }


        public Builder merchantCode(@NonNull String merchantCode) {
            this.merchantCode = merchantCode;
            return this;
        }

        public Builder terminal(@NonNull String terminal) {
            this.terminal = terminal;
            return this;
        }

        public Builder transactionType(@NonNull TransactionType transactionType) {
            this.transactionType = transactionType.getCode();
            return this;
        }

        public Builder currency(@NonNull Currency currency) {
            this.currency = currency.getISOCodeNumeric();
            return this;
        }

        public Builder consumerLanguage(@NonNull Language consumerLanguage) {
            this.consumerLanguage = Integer.valueOf(consumerLanguage.getCode());
            return this;
        }

        public Builder order(@NonNull String order) {
            this.order = order;
            return this;
        }

        public Builder amount(@NonNull long amount) {
            this.amount = amount;
            return this;
        }

        public Builder urlOk(@NonNull String urlOk) {
            this.urlOk = urlOk;
            return this;
        }

        public Builder urlKo(@NonNull String urlKo) {
            this.urlKo = urlKo;
            return this;
        }

        public Builder urlNotification(@NonNull String urlNotification) {
            this.urlNotification = urlNotification;
            return this;
        }

        public Builder productDescription(@NonNull String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder payMethods(@NonNull RedsysPaymentMethod paymentMethods) {
            this.payMethods = paymentMethods.getCode();
            return this;
        }

        public Builder titular(@NonNull String titular) {
            this.titular = titular;
            return this;
        }

        public Builder merchantName(@NonNull String merchantName) {
            this.merchantName = merchantName;
            return this;
        }

        public OrderCES build() {
            OrderCES orderCES =  new OrderCES();

            orderCES.setDs_merchant_merchantcode(merchantCode);
            orderCES.setDs_merchant_terminal(terminal);
            orderCES.setDs_merchant_transactiontype(transactionType);
            orderCES.setDs_merchant_currency(currency);
            orderCES.setDs_merchant_consumerLanguage(consumerLanguage);
            orderCES.setDs_merchant_order(order);
            orderCES.setDs_merchant_amount(amount);
            orderCES.setDs_merchant_merchantURL(urlNotification);
            orderCES.setDs_merchant_UrlOK(urlOk);
            orderCES.setDs_merchant_UrlKO(urlKo);
            orderCES.setDs_merchant_productdescription(productDescription);
            orderCES.setDs_merchant_paymethods(payMethods);

            orderCES.setDs_merchant_merchantName(merchantName);
            orderCES.setDs_merchant_titular(titular);

            orderCES.assertIsSet();

            return orderCES;
        }
    }
}
