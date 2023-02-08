package com.bogdanovstudio;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    private final TreeMap<Customer, String> dataByCustomersSortedByCustomersScore = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        var potentialSmallest = dataByCustomersSortedByCustomersScore.firstEntry();
        return potentialSmallest != null ? Map.entry(new Customer(potentialSmallest.getKey()), potentialSmallest.getValue()) : null;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var potentialNext = dataByCustomersSortedByCustomersScore.higherEntry(customer);
        return potentialNext != null ? Map.entry(new Customer(potentialNext.getKey()), potentialNext.getValue()) : null;
    }

    public void add(Customer customer, String data) {
        dataByCustomersSortedByCustomersScore.put(customer, data);
    }
}
