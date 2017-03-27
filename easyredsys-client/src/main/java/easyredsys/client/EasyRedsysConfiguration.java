package easyredsys.client;

import easyredsys.client.core.Notification;
import easyredsys.client.util.RedsysAddresses;
import easyredsys.security.SecurityPolicy;

public interface EasyRedsysConfiguration {

    public String getMerchantCode();
    public String getTerminal();
    public String getSecretKey();
    public String getRedsysUrl();

    public SecurityPolicy getSecurityPolicy();
}
