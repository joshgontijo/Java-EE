/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.it.test;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Josue
 */
public class UserPersistenceIT {

    EntityTransaction tx;
    UserPersistence persistence;

    @Before
    public void init() {
        persistence = new UserPersistence();
        persistence.em = Persistence.createEntityManagerFactory("TEST-PU").createEntityManager();
        this.tx = persistence.em.getTransaction();

        tx.begin();
    }

    @After
    public void dispose() {
        tx.rollback();
    }

    @Test
    public void testListUsers() {
        persistence.create(new User("josue"));
        persistence.create(new User("eduardo"));
        persistence.create(new User("gontijo"));
        List<User> foundusers = persistence.getUsers();
        Assert.assertEquals(3, foundusers.size());
    }

    @Test
    public void testUpdate() {
        String newName = "New Name";
        User created = new User("josue");
        persistence.create(created);
        created.setName(newName);
        User updated = persistence.update(created);
        Assert.assertEquals(newName, updated.getName());

    }

}
