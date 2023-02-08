package com.bogdanovstudio;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    private final Deque<Customer> customersStack = new ArrayDeque<>();

    public void add(Customer customer) {
        customersStack.push(customer);
    }

    public Customer take() {
        return customersStack.pop();
    }
}
