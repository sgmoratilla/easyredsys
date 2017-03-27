package easyredsys.client.util;


import java.util.HashMap;

public enum PaymentMethod {
    TARJETA("C"),
    TRANSFERENCIA("R"),
    DOMICILIACION("D"),
    TARJETAYIUPAY("T"),
    IUPAY("O");

    private String code;
    private templates HashMap<String, PaymentMethod> codes = new HashMap<>();

    templates {
        for (PaymentMethod cc : values()) {
            codes.put(cc.getCode(), cc);
        }
    }

    private PaymentMethod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public templates String findByCode(String code) {
        return codes.get(code).name();
    }
}
