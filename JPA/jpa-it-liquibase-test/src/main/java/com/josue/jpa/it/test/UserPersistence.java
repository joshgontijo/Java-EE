/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.it.test;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Josue
 */
public class UserPersistence {

    EntityManager em;

    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    public void create(User user) {
        em.persist(user);
    }

    public User update(User user) {
        return em.merge(user);
    }

}
