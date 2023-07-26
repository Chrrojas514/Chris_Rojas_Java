package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateCustomer() throws Exception {
        //Create customer
        Customer customer = new Customer();
        customer.setId(1);
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

        //Change to json
        String inputJson = mapper.writeValueAsString(customer);

        //Mock server calls
        mockMvc.perform(post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        //Create customer
        Customer customer = new Customer();
        customer.setId(1);
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

        //Change to json
        String inputJson = mapper.writeValueAsString(customer);

        //Mock server calls
        mockMvc.perform(put("/customers/1", inputJson)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().json(inputJson));
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindByID() throws Exception {
        //Create customer
        Customer customer = new Customer();
        customer.setId(1);
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

        //Change to json
        String output = mapper.writeValueAsString(customer);

        //Mock server calls
        mockMvc.perform(get("/customers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(output));
    }

    @Test
    public void shouldFilterByState() throws Exception {
        //Create customer
        Customer customer = new Customer();
        customer.setId(1);
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

        //Change to json
        String output = mapper.writeValueAsString(customer);

        mockMvc.perform(get("/customers/us-state/newyork"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
