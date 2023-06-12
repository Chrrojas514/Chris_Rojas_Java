package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        //Update this
        List<String> uniqueCustomers = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();

        for (String[] customer : customerData) {
            if ( !uniqueCustomers.contains(customer[1]) ) {
                uniqueCustomers.add(customer[1]);

                Customer newCustomer = new Customer();
                newCustomer.setId(Integer.parseInt(customer[0]));
                newCustomer.setName(customer[1]);

                customerList.add(newCustomer);
            }

            int customerID = Integer.parseInt(customer[0]);

            AccountRecord charge = new AccountRecord();
            charge.setCharge(Integer.parseInt(customer[2]));
            charge.setChargeDate(customer[3]);

            Customer currentCustomer = customerList.stream()
                            .filter(targetCustomer -> targetCustomer.getId() == customerID)
                            .findFirst().get();
            currentCustomer.getCharges().add(charge);
        }

        System.out.println("Positive accounts:");
        customerList.stream()
                    .filter(targetCustomers -> targetCustomers.getBalance() > 0)
                    .forEach(System.out::println);

        System.out.println("/////////////////////////////////////////////////////////////////\n");

        System.out.println("Negative accounts:");
        customerList.stream()
                .filter(targetCustomers -> targetCustomers.getBalance() < 0)
                .forEach(System.out::println);
    }
}
