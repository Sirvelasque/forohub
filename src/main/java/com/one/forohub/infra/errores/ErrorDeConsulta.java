package com.one.forohub.infra.errores;

public class ErrorDeConsulta extends RuntimeException {
    public ErrorDeConsulta(String message) {
        super(message);
    }
}
