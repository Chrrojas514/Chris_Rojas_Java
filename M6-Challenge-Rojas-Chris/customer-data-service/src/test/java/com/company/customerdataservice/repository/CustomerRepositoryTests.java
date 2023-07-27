package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository repo;

    @BeforeEach
    public void setup() {
        repo.deleteAll();
    }

    @Test
    public void shouldCreateCustomer() {
        //Create customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("New York");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        //Have the DB assign an ID
        customer = repo.save(customer);

        //Search DB for customer
        Optional<Customer> checkCust = repo.findById(customer.getId());

        //Assert
        assertEquals(customer, checkCust.get());
    }

    @Test
    public void shouldUpdateCustomer() {
        //Create customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("New York");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        //Have the DB assign an ID
        customer = repo.save(customer);

        //Update customer company
        customer.setCompany("smallcompany");

        //Push update to db and fetch customer for assertion
        repo.save(customer);
        Optional<Customer> checkCust = repo.findById(customer.getId());

        //Assert
        assertEquals(customer, checkCust.get());
    }

    @Test
    public void shouldDeleteCustomer() {
        //Create customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("New York");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        //Have the DB assign an ID
        customer = repo.save(customer);

        //Check to make sure customer was added
        Optional<Customer> custCheck = repo.findById(customer.getId());
        assertEquals(customer, custCheck.get());

        //Delete record
        repo.deleteById(customer.getId());

        //Attempt to fetch record
        Optional<Customer> checkIfPresent = repo.findById(customer.getId());

        assertFalse(checkIfPresent.isPresent());
    }

    @Test
    public void shouldReturnCustomerByID() {
        //Create customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("New York");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        // Have DB assign an ID
        customer = repo.save(customer);
        Integer searchFor = customer.getId();

        //Fetch customer by ID
        Optional<Customer> custCheck = repo.findById(searchFor);

        //Assert
        assertEquals(customer, custCheck.get());
    }

    @Test
    public void shouldGetCustomerRecordsByState() {
        //Create customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("New York");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        //Have the DB assign an ID
        customer = repo.save(customer);

        //Create customer from another state
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        customer1.setEmail("johnsmith@mail.com");
        customer1.setCompany("BigCompany");
        customer1.setPhone("646-535-4242");
        customer1.setAddressOne("446 Rockville Rd");
        customer1.setAddressTwo("PO 1655 Main St");
        customer1.setCity("Bronx");
        customer1.setState("California");
        customer1.setPostalCode("10470");
        customer1.setCountry("United States");

        //Have the DB assign an ID
        customer1 = repo.save(customer1);

        //Retrieve customers from New York
        List<Customer> custFromNY = repo.findAllCustomersByState("New York");

        //Assert
        assertEquals(custFromNY.get(0), customer);
    }
}
