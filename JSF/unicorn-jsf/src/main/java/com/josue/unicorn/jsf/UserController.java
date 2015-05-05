/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.unicorn.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class UserController implements Serializable {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    private List<User> users = new ArrayList<>();
    private User user = new User();
    private String successMessage;

    @PostConstruct
    public void init() {
        users.add(new User(1, "josue"));
        users.add(new User(2, "eduardo"));
        LOG.info("************** @PostConstruct ******************");
    }

    public String refresh() {
        return "index.xhtml?faces-redirect=true";
    }

    public void createUser() {
        users.add(user);
        user = new User();

        successMessage = "User created !!!";
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
