package com.bogdanovstudio;

import com.bogdanovstudio.interfaces.ATM;

public class Main {
    public static void main(String[] args) {
        ATM atm = new SimpleATM();
        System.out.println("Initial ATM balance: " + atm.getBalance());

        var batch = new BanknoteBatch();
        // 15_000
        var stack = new BanknoteStack(Banknote.FIVE_THOUSAND, 3);
        batch.addBanknoteStacks(stack);
        // 6_000
        stack = new BanknoteStack(Banknote.TWO_THOUSAND, 3);
        batch.addBanknoteStacks(stack);
        // 3_000
        stack = new BanknoteStack(Banknote.ONE_THOUSAND, 3);
        atm.putMoney(stack);
        // 1_500
        atm.putMoney(Banknote.FIVE_HUNDRED);
        atm.putMoney(Banknote.FIVE_HUNDRED);
        atm.putMoney(Banknote.FIVE_HUNDRED);
        // 600
        stack = new BanknoteStack(Banknote.TWO_HUNDRED, 3);
        batch.addBanknoteStacks(stack);
        // 300
        stack = new BanknoteStack(Banknote.ONE_HUNDRED, 3);
        batch.addBanknoteStacks(stack);
        // 150
        stack = new BanknoteStack(Banknote.FIFTY, 3);
        batch.addBanknoteStacks(stack);
        // 30
        stack = new BanknoteStack(Banknote.TEN, 3);
        batch.addBanknoteStacks(stack);

        atm.putMoney(batch);
        System.out.println("ATM balance after replenishment: " + atm.getBalance());
        for (int i = 0; i < 4; i++) {
            System.out.println("------------------------------" + i);
            System.out.println("get 600");
            var money = atm.getMoney(600);
            System.out.println(money);
            System.out.println("ATM balance after withdraw: " + atm.getBalance());
        }

        System.out.println("------------------------------");
        System.out.println("get 180");
        var money = atm.getMoney(180);
        System.out.println(money);
        System.out.println("ATM balance after withdraw: " + atm.getBalance());
        System.out.println(atm);

        System.out.println("------------------------------");
        System.out.println("get 13000");
        money = atm.getMoney(13000);
        System.out.println(money);
        System.out.println("ATM balance after withdraw: " + atm.getBalance());
        System.out.println(atm);
    }
}