package easyredsys.client.util;

import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.security.SecurityPolicy;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Default implementation of the configuration that requires all params
 * to be specified in the constructor.
 */
public class DefaultEasyRedsysConfiguration implements EasyRedsysConfiguration {

    private String merchantCode;
    private String terminal;
    private String secretKey;
    private String redsysUrl;


    public DefaultEasyRedsysConfiguration(@NonNull String merchantCode, @NonNull String terminal,
                                          @NonNull String secretKey, @NonNull String redsysUrl) {
        this.merchantCode = merchantCode;
        this.terminal = terminal;
        this.secretKey = secretKey;
        this.redsysUrl = redsysUrl;
    }

    @Override
    public String getMerchantCode() { return merchantCode; }
    @Override
    public String getTerminal() { return terminal; }
    @Override
    public String getSecretKey() { return secretKey; }
    @Override
    public String getRedsysUrl() { return redsysUrl; }

    public SecurityPolicy getSecurityPolicy() {
        return new SecurityPolicy();
    }
}
