package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer createCustomer(int id, String name) {
        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        newCustomer.setName(name);

        return newCustomer;
    }

    private AccountRecord createRecord(int charge, String chargeDate) {
        AccountRecord newRecord = new AccountRecord();
        newRecord.setCharge(charge);
        newRecord.setChargeDate(chargeDate);

        return newRecord;
    }

    @Test
    void getBalance() {
        Customer inDebt = createCustomer(1, "Delinquent");
        Customer posCustomer = createCustomer(2, "Good");
        Customer thirdCustomer = createCustomer(3, "The Third");
        Customer negCustomer = createCustomer(4, "Negative");
        Customer surplusCustomer = createCustomer(5, "In The Positive");

        AccountRecord largeNegative = createRecord(-10000, "05-28-23");
        AccountRecord smallNegative = createRecord(-1000, "05-28-23");
        AccountRecord largePositive = createRecord(10000, "05-28-23");
        AccountRecord smallPositive = createRecord(1000, "05-28-23");

        inDebt.getCharges().add(largeNegative);
        inDebt.getCharges().add(largeNegative);
        assertEquals(-20000, inDebt.getBalance());

        posCustomer.getCharges().add(largePositive);
        posCustomer.getCharges().add(largePositive);
        assertEquals(20000, posCustomer.getBalance());

        thirdCustomer.getCharges().add(largeNegative);
        thirdCustomer.getCharges().add(smallPositive);
        assertEquals(-9000, thirdCustomer.getBalance());

        negCustomer.getCharges().add(largeNegative);
        negCustomer.getCharges().add(smallNegative);
        assertEquals(-11000, negCustomer.getBalance());

        surplusCustomer.getCharges().add(largePositive);
        surplusCustomer.getCharges().add(smallNegative);
        assertEquals(9000, surplusCustomer.getBalance());
    }

    @Test
    void testToString() {
        Customer inDebt = createCustomer(1, "Delinquent");
        Customer posCustomer = createCustomer(2, "Good");
        Customer thirdCustomer = createCustomer(3, "The Third");

        AccountRecord largeNegative = createRecord(-10000, "05-28-23");
        AccountRecord smallNegative = createRecord(-1000, "05-28-23");
        AccountRecord largePositive = createRecord(10000, "05-28-23");
        AccountRecord smallPositive = createRecord(1000, "05-28-23");

        inDebt.getCharges().add(largeNegative);
        inDebt.getCharges().add(largeNegative);

        posCustomer.getCharges().add(largePositive);
        posCustomer.getCharges().add(largePositive);

        thirdCustomer.getCharges().add(largeNegative);
        thirdCustomer.getCharges().add(smallPositive);

        String orderInDebt = String.format("Customer ID: %d\n" +
                        "Customer Name: %s\n" +
                        "Customer Balance: %d\n",
                        inDebt.getId(), inDebt.getName(), inDebt.getBalance() );
        String orderPosCustomer = String.format("Customer ID: %d\n" +
                        "Customer Name: %s\n" +
                        "Customer Balance: %d\n",
                posCustomer.getId(), posCustomer.getName(), posCustomer.getBalance() );
        String orderThirdCustomer = String.format("Customer ID: %d\n" +
                        "Customer Name: %s\n" +
                        "Customer Balance: %d\n",
                thirdCustomer.getId(), thirdCustomer.getName(), thirdCustomer.getBalance() );

        assertEquals(orderInDebt, inDebt.toString());
        assertEquals(orderPosCustomer, posCustomer.toString());
        assertEquals(orderThirdCustomer, thirdCustomer.toString());
    }
}