package com.bogdanovstudio;

import java.util.LinkedList;

public class CustomerReverseOrder {
    private final LinkedList<Customer> customersStack = new LinkedList<>();

    public void add(Customer customer) {
        customersStack.push(customer);
    }

    public Customer take() {
        return customersStack.pop();
    }
}
