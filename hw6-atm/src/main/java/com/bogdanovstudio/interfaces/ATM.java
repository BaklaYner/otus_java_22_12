package com.bogdanovstudio.interfaces;

import com.bogdanovstudio.Banknote;
import com.bogdanovstudio.BanknoteBatch;
import com.bogdanovstudio.BanknoteStack;

public interface ATM {
    int getBalance();
    void putMoney(BanknoteBatch batch);
    void putMoney(BanknoteStack stack);
    void putMoney(Banknote banknote);
    BanknoteBatch getMoney(int amount);
}