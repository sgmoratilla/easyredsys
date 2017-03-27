package easyredsys.client.client.core;

import sis.redsys.api.ApiWsMacSha256;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NotificationNoCES extends Notification {

    @Override
    public boolean isValid(String claveSecreta, String expectedSignature) {

        String signature = "";
        try {
            ApiWsMacSha256 apiWsMacSha256 = new ApiWsMacSha256();

            signature = apiWsMacSha256.createSignatureResponseHostToHost(claveSecreta, getCadenaConcatenada(), getDs_Order());
        } catch (UnsupportedEncodingException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return !signature.isEmpty() && signature.equals(expectedSignature);
    }

    private String getCadenaConcatenada() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDs_Amount());
        sb.append(getDs_Order());
        sb.append(getDs_MerchantCode());
        sb.append(getDs_Currency());
        sb.append(getDs_Response());
        sb.append(getDs_TransactionType());
        sb.append(getDs_SecurePayment());

        return sb.toString();
    }

    private static final Logger _log = Logger.getLogger(NotificationNoCES.class.getName());

}
