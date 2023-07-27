package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private List<Customer> customerList = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    private void setup() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        customer1.setEmail("johnsmith@mail.com");
        customer1.setCompany("BigCompany");
        customer1.setPhone("646-535-4242");
        customer1.setAddressOne("446 Rockville Rd");
        customer1.setAddressTwo("PO 1655 Main St");
        customer1.setCity("Bronx");
        customer1.setState("ny");
        customer1.setPostalCode("10470");
        customer1.setCountry("United States");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Smith");
        customer2.setLastName("Joahn");
        customer2.setEmail("smithsonian@mail.com");
        customer2.setCompany("little company");
        customer2.setPhone("929-535-4242");
        customer2.setAddressOne("123 Mount View");
        customer2.setAddressTwo("165th St Northern Blvd");
        customer2.setCity("Queens");
        customer2.setState("ny");
        customer2.setPostalCode("11370");
        customer2.setCountry("United States");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Morgan");
        customer3.setLastName("Freeman");
        customer3.setEmail("morgan@mail.com");
        customer3.setCompany("acting company");
        customer3.setPhone("843-535-4242");
        customer3.setAddressOne("15 Church St");
        customer3.setAddressTwo("52 St Jacob Drive");
        customer3.setCity("Charleston");
        customer3.setState("sc");
        customer3.setPostalCode("29461");
        customer3.setCountry("United States");

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        //Create customer
        Customer customer = new Customer();
        customer.setId(10);
        customer.setFirstName("John");
        customer.setLastName("Smith");
        customer.setEmail("johnsmith@mail.com");
        customer.setCompany("BigCompany");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("NY");
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
        customer.setState("NY");
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
                .andExpect(status().isNoContent());
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
        customer.setFirstName("Batista");
        customer.setLastName("Smith");
        customer.setEmail("batistaSmith@mail.com");
        customer.setCompany("wwe");
        customer.setPhone("646-535-4242");
        customer.setAddressOne("446 Rockville Rd");
        customer.setAddressTwo("PO 1655 Main St");
        customer.setCity("Bronx");
        customer.setState("California");
        customer.setPostalCode("90470");
        customer.setCountry("us");

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
        customer.setState("Ohio");
        customer.setPostalCode("10470");
        customer.setCountry("United States");

        mockMvc.perform(get("/customers/us/ohio"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
