package com.bogdanovstudio;

public enum Banknote {
    FIVE_THOUSAND(5000), TWO_THOUSAND(2000), ONE_THOUSAND(1000), FIVE_HUNDRED(500),
    TWO_HUNDRED(200), ONE_HUNDRED(100), FIFTY(50), TEN(10);
    private final int denomination;

    Banknote(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}