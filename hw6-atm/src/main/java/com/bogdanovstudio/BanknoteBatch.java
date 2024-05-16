package com.bogdanovstudio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BanknoteBatch {
    private final List<BanknoteStack> banknoteStacks = new ArrayList<>();

    public BanknoteBatch(BanknoteStack... stacks) {
        banknoteStacks.addAll(List.of(stacks));
    }

    public BanknoteBatch(List<BanknoteStack> stacks) {
        banknoteStacks.addAll(stacks);
    }

    public void addBanknoteStacks(BanknoteStack... stacks) {
        banknoteStacks.addAll(List.of(stacks));
    }

    public void addBanknoteStacks(List<BanknoteStack> stacks) {
        banknoteStacks.addAll(stacks);
    }

    public void addBanknotes(Banknote... banknotes) {
        addBanknoteStacks(Arrays.stream(banknotes).map(BanknoteStack::new).toList());
    }

    public void addBanknotes(List<Banknote> banknotes) {
        addBanknoteStacks(banknotes.stream().map(BanknoteStack::new).toList());
    }

    public List<BanknoteStack> getBanknoteStacks() {
        var stacks = List.copyOf(banknoteStacks);
        banknoteStacks.clear();
        return stacks;
    }

    @Override
    public String toString() {
        return "BanknoteBatch{" + banknoteStacks +
                '}';
    }
}