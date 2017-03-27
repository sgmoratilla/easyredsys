package easyredsys.client.client.util;

public enum TransactionType {
    AUTORIZACION("0"),
    PRE_AUTORIZACION("1"),
    CONFIRMACION_PRE_AUTORIZACION("2"),
    DEVOLUCION_AUTOMATICA("3"),
    CUOTA_INICIAL("5"), // TRANSACCION_RECURRENTE
    CUOTA_SUCESIVA("6"), // TRANSACCION_SUCESIVA
    PRE_AUTENTICACION("7"),
    CONFIRMACION_PRE_AUTENTICACION("8"),
    ANULACION_PRE_AUTORIZACION("9"),
    AUTORIZACION_DIFERIDO("O"),
    CONFIRMACION_AUTORIZACION_DIFERIDO("P"),
    ANULACION_AUTORIZACION_DIFERIDO("Q"),
    CUOTA_INICIAL_DIFERIDO("R"),
    CUOTA_SUCESIVA_DIFERIDO("S"),

    TARJETA_EN_ARCHIVO_INICIAL("L"),
    TARJETA_EN_ARCHIVO_SUCESIVA("M");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
