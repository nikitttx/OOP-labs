package org.example.exceptions;

public class IllegalScaleFactorException extends RuntimeException {
    public IllegalScaleFactorException() {
        super("Scale factor must be greater than zero");
    }
}
