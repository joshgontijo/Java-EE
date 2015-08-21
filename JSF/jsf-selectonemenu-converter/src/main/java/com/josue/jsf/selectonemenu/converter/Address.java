/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caci.jsf.selectonemenu.converter;

import java.util.Objects;

/**
 *
 * @author jgontijo
 */
public class Address {

    private int id;
    private String street;

    public Address(int id, String street) {
        this.id = id;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.street);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.street, other.street);
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + '}';
    }

}
