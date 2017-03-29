package easyredsys.client.util;


import java.util.HashMap;

/**
 * Possible payment methods in Redsys.
 * In Spanish because the official documentation
 * describes them in Spanish.
 */
public enum RedsysPaymentMethod {
    TARJETA("C"),
    TRANSFERENCIA("R"),
    DOMICILIACION("D"),
    TARJETAYIUPAY("T"),
    IUPAY("O");

    private String code;
    private static HashMap<String, RedsysPaymentMethod> codes = new HashMap<>();

    static {
        for (RedsysPaymentMethod cc : values()) {
            codes.put(cc.getCode(), cc);
        }
    }

    RedsysPaymentMethod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String findByCode(String code) {
        return codes.get(code).name();
    }
}
