package com.josue.jee.batch.csv.database;

import java.io.Serializable;

/**
 * @author Arun Gupta
 */
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String zipCode;
    private String email;
    private String username;
    private String telephone;

    public Person(String firstName, String lastName, String gender, String city, String zipCode, String email, String username, String telephone) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
        this.zipCode = zipCode;
        this.email = email;
        this.username = username;
        this.telephone = telephone;
    }

    public Person() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
