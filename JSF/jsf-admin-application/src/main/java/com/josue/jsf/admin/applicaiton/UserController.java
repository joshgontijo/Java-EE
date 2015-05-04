/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.admin.applicaiton;

import com.josue.jsf.admin.applicaiton.entity.AppUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Josue
 */
@Named
@RequestScoped
public class UserController implements Serializable {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private AppUser user = new AppUser();
    private List<AppUser> users = new ArrayList<>();

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        logger.info("********* Initializing USER CONTROLLER *************");
        if (users.isEmpty()) {
            fetchData();
        }
    }

    @Transactional
    public void createUser() {
        em.persist(user);
        user = new AppUser();
        fetchData();
    }

    @Transactional
    public void deleteUser(AppUser user) {
        AppUser foundUser = em.find(AppUser.class, user.getId());
        if (foundUser != null) {
            em.remove(foundUser);
        }
        fetchData();
    }

//    public List<AppUser> initializeUsers() {
//        if (users.isEmpty()) {
//            logger.info("************** USERS IS EMPTY --- FETCHING DATA *************");
//            fetchData();
//        } else {
//            logger.info("************** USERS ALREADY LOADED --- *************");
//        }
//        return users;
//    }
    public void fetchData() {
        logger.info("************** FETCHING DATA *************");
        users = em.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

}
