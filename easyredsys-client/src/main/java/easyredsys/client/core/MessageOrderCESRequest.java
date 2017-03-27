package easyredsys.client.core;


import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.util.RedsysAddresses;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageOrderCESRequest {
    private static final Logger _log = Logger.getLogger(MessageOrderCESRequest.class.getName());

    private OrderCES orderCES;

    public MessageOrderCESRequest(OrderCES orderCES) {
        this.orderCES = orderCES;
    }

    public String getDs_MerchantParameters() {

        String merchanParameters = "";

        try {
            merchanParameters = orderCES.apiMacSha256.createMerchantParameters();
        } catch (UnsupportedEncodingException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return merchanParameters;
    }

    public String getDs_Signature(String secretKey) {

        String dsSignature = "";

        try {
            dsSignature = orderCES.apiMacSha256.createMerchantSignature(secretKey);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return dsSignature;
    }

    public String getDs_SignatureVersion() {
        return orderCES.getDs_SignatureVersion();
    }

    public OrderCES getOrderCES() {
        return orderCES;
    }

    public void setOrderCES(OrderCES orderCES) {
        this.orderCES = orderCES;
    }
}
