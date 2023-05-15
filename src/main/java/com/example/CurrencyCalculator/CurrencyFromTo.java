package com.example.CurrencyCalculator;

public class CurrencyFromTo {
    String from;
    String to;

    public CurrencyFromTo() {
    }

    public CurrencyFromTo(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
