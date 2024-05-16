package com.bogdanovstudio;

import com.bogdanovstudio.interfaces.ATM;

import java.util.*;

public class SimpleATM implements ATM {
    private final Map<Banknote, Integer> cells = new EnumMap<>(Banknote.class);
    private int balance = 0;
    private List<Banknote> availableBanknotes;
    private int minimalAvailableDenomination = 0;

    public SimpleATM() {
        for (Banknote banknote : Banknote.values()) {
            cells.put(banknote, 0);
        }
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void putMoney(BanknoteBatch batch) {
        batch.getBanknoteStacks().forEach(this::putMoney);
    }

    @Override
    public void putMoney(BanknoteStack stack) {
        var type = stack.banknoteType();
        cells.put(type, cells.get(type) + stack.banknoteQuantity());

        collectAvailableDenominations();
    }

    @Override
    public void putMoney(Banknote banknote) {
        this.putMoney(new BanknoteStack(banknote));
    }

    @Override
    public BanknoteBatch getMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Requested amount should be positive");
        }

        if (balance < amount) {
            throw new IllegalArgumentException("Requested amount greater than available balance");
        }

        if (amount % minimalAvailableDenomination != 0) {
            throw new IllegalArgumentException("Requested amount should be multiple of " + minimalAvailableDenomination);
        }

        var result = tryToCollectAmount(amount);
        if (result == null) {
            throw new IllegalArgumentException("Requested amount can't be collected");
        }

        return result;
    }

    @Override
    public String toString() {
        return "SimpleATM{" +
                "cells=" + cells +
                ", balance=" + balance +
                ", availableBanknotes=" + availableBanknotes +
                ", minimalAvailableDenomination=" + minimalAvailableDenomination +
                '}';
    }

    private void collectAvailableDenominations() {
        availableBanknotes = cells.entrySet().stream()
                .filter(banknoteIntegerEntry -> banknoteIntegerEntry.getValue() > 0)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Banknote::getDenomination).reversed())
                .toList();

        minimalAvailableDenomination = availableBanknotes.stream()
                .map(Banknote::getDenomination)
                .min(Integer::compareTo)
                .orElse(0);

        balance = availableBanknotes.stream().mapToInt(banknote -> cells.get(banknote) * banknote.getDenomination()).sum();
    }

    private BanknoteBatch tryToCollectAmount(int amount) {
        var stacks = new ArrayList<BanknoteStack>();

        var leastAmount = amount;
        for (var banknote : availableBanknotes) {
            if (leastAmount == 0) {
                break;
            }

            var denomination = banknote.getDenomination();
            if (denomination > leastAmount) {
                continue;
            }

            var quantity = leastAmount / denomination;
            if (quantity > cells.get(banknote)) {
                quantity = cells.get(banknote);
            }

            stacks.add(new BanknoteStack(banknote, quantity));
            leastAmount -= (quantity * denomination);
        }
        if (leastAmount != 0) {
            return null;
        }

        stacks.forEach(stack -> cells.put(stack.banknoteType(), cells.get(stack.banknoteType()) - stack.banknoteQuantity()));
        collectAvailableDenominations();

        return new BanknoteBatch(stacks);
    }
}