/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.simple.beanvalidation.entity;

import com.josue.simple.beanvalidation.validation.Create;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Josue
 */
public class User {

    @NotNull(groups = Create.class)
    private String name;
    private int age;

    @Valid //mark as validation cascading
    private Address address;
    private Date birthdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

}
