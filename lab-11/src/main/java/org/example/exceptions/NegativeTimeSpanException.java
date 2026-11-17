package org.example.exceptions;

public class NegativeTimeSpanException extends RuntimeException {
    public NegativeTimeSpanException() {
        super("Time span cannot be negative");
    }
}
