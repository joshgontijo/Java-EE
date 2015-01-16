/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.simple.beanvalidation.entity;

import com.josue.simple.beanvalidation.validation.Create;
import com.josue.simple.beanvalidation.validation.Update;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Josue
 */
public class Address {

    @NotNull(groups = {Create.class, Update.class})
    private String street;

    @Digits(integer = 2, fraction = 0, groups = Update.class)
    private int number;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
