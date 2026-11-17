package org.example;

import org.example.exceptions.IllegalInputTimeSpanException;
import org.example.exceptions.IllegalScaleFactorException;
import org.example.exceptions.NegativeTimeSpanException;

public class TimeSpan {
    private int totalMinutes;

    public TimeSpan() {
        this(0);
    }

    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalInputTimeSpanException();
        }
        totalMinutes = minutes;
    }

    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalInputTimeSpanException();
        }
        totalMinutes = Math.addExact(Math.multiplyExact(hours, 60), minutes);
    }

    public TimeSpan(TimeSpan span) {
        this(span.getHours(), span.getMinutes());
    }

    public int getHours() {
        return totalMinutes / 60;
    }

    public int getMinutes() {
        return totalMinutes % 60;
    }

    public void add(int hours, int minutes) {
        add(new TimeSpan(hours, minutes));
    }

    public void add(int minutes) {
        add(new TimeSpan(minutes));
    }

    public void add(TimeSpan span) {
        totalMinutes = Math.addExact(totalMinutes, span.getTotalMinutes());
    }

    public void subtract(int hours, int minutes) {
        subtract(new TimeSpan(hours, minutes));
    }

    public void subtract(int minutes) {
        subtract(new TimeSpan(minutes));
    }

    public void subtract(TimeSpan span) {
        if (span.getTotalMinutes() > totalMinutes) {
            throw new NegativeTimeSpanException();
        }
        totalMinutes -= span.getTotalMinutes();
    }

    public double getTotalHours() {
        return totalMinutes / 60.0;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void scale(int factor) {
        if (factor <= 0) {
            throw new IllegalScaleFactorException();
        }
        totalMinutes = Math.multiplyExact(totalMinutes, factor);
    }

    @Override
    public String toString() {
        return getHours() + " h " + getMinutes() + " min";
    }
}
