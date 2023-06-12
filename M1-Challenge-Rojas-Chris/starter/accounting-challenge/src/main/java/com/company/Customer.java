package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        //Used https://www.baeldung.com/java-stream-reduce as a resource for below stream
        return charges.stream()
                .reduce(0, (totalDue, currentCharge) -> totalDue + currentCharge.getCharge(),
                        Integer::sum);
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        return String.format("Customer ID: %d\n" +
                "Customer Name: %s\n" +
                "Customer Balance: %d\n",
                getId(), getName(), getBalance() );
    }
}
