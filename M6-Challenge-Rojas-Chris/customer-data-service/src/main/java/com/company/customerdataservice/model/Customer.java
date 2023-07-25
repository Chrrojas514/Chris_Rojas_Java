package com.company.customerdataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String phone;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) && Objects.equals(getFirstName(),
                customer.getFirstName()) && Objects.equals(getLastName(),
                customer.getLastName()) && Objects.equals(getEmail(),
                customer.getEmail()) && Objects.equals(getCompany(),
                customer.getCompany()) && Objects.equals(getPhone(),
                customer.getPhone()) && Objects.equals(getAddressOne(),
                customer.getAddressOne()) && Objects.equals(getAddressTwo(),
                customer.getAddressTwo()) && Objects.equals(getCity(),
                customer.getCity()) && Objects.equals(getState(),
                customer.getState()) && Objects.equals(getPostalCode(),
                customer.getPostalCode()) && Objects.equals(getCountry(),
                customer.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getFirstName(),
                getLastName(),
                getEmail(),
                getCompany(),
                getPhone(),
                getAddressOne(),
                getAddressTwo(),
                getCity(),
                getState(),
                getPostalCode(),
                getCountry());
    }
}
