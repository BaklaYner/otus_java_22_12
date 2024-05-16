package com.bogdanovstudio;

public record BanknoteStack(Banknote banknoteType, int banknoteQuantity) {
    public BanknoteStack {
        if (banknoteQuantity <= 0) {
            throw new IllegalArgumentException("Banknote's banknoteQuantity should be positive number");
        }
    }

    public BanknoteStack(Banknote banknote) {
        this(banknote, 1);
    }
}