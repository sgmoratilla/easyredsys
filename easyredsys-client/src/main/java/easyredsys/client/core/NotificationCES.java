package easyredsys.client.core;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NotificationCES extends Notification {
    private static final Logger LOGGER = Logger.getLogger(NotificationCES.class.getName());


    private String ds_MerchantParameters;

    @Override
    public boolean isValid(String secretKey, String expectedSignature) {
        try {
            String signature = getApiMacSha256().createMerchantSignatureNotif(secretKey, ds_MerchantParameters);

            return !signature.isEmpty() && signature.equals(expectedSignature);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidAlgorithmParameterException | BadPaddingException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }

        return false;
    }

    public String getDs_MerchantParameters() {
        return ds_MerchantParameters;
    }

    public void setDs_MerchantParameters(String ds_MerchantParameters) {
        this.ds_MerchantParameters = ds_MerchantParameters;
    }


}
