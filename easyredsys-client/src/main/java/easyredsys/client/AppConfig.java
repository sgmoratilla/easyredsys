package easyredsys.client.client;

import easyredsys.client.client.core.Notification;

public interface AppConfig {

    static String getMerchantCode() { return "0";}
    static String getTerminal() { return "0";}
    static String getSecretKey() { return "sq7HjrUOBfKmC576ILgskD5srU870gJ7";}
    static boolean isTestMode() { return true;}

    void saveNotification(Notification notification);

    default String getCushysellToken() { return "";}
}
