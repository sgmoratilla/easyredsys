package easyredsys;

import easyredsys.client.EasyRedsysConfiguration;
import easyredsys.client.core.Notification;
import easyredsys.client.util.RedsysAddresses;
import easyredsys.security.SecurityPolicy;

public class TestEasyRedsysConfiguration implements EasyRedsysConfiguration {
    @Override
    public String getMerchantCode() {
        return "061978060";
    }

    @Override
    public String getTerminal() {
        return "001";
    }

    @Override
    public String getSecretKey() {
        return "sq7HjrUOBfKmC576ILgskD5srU870gJ7";
    }

    @Override
    public String getRedsysUrl() {
        // FIXME: Don't use hardcoded URLs.
        return RedsysAddresses.getRedirectURL("test");
    }

    @Override
    public SecurityPolicy getSecurityPolicy() {
        return new SecurityPolicy();
    }
}
