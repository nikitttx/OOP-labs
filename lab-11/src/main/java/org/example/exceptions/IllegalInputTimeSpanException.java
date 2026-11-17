package org.example.exceptions;

public class IllegalInputTimeSpanException extends RuntimeException {
    public IllegalInputTimeSpanException() {
        super("Hours must be non-negative; minutes must be 0 to 59 for two arguments");
    }
}
