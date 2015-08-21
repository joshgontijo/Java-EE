/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caci.jsf.selectonemenu.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author jgontijo
 */
@Named
@ViewScoped
public class Boundary implements Serializable {

    private static final Logger logger = Logger.getLogger(Boundary.class.getName());

    private List<User> users;
    private List<Address> addresses;

    private Address selectedAddress;
    private User selectedUser;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        users.add(new User(1, "user1"));
        users.add(new User(2, "user2"));
        users.add(new User(3, "user3"));

        addresses = new ArrayList<>();
        addresses.add(new Address(1, "Old"));
        addresses.add(new Address(2, "Oak"));
        addresses.add(new Address(3, "London"));
        addresses.add(new Address(4, "Campinas"));

    }

    public void onChange() {
        logger.log(Level.INFO, "USER :: {0}", selectedUser);
        logger.log(Level.INFO, "ADDRESS :: {0}", selectedAddress);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getSelectedAddress() {
        return selectedAddress;
    }

    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}
