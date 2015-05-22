/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.it.test;

import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Josue
 */
public class UserPersistenceIT {

    static EntityTransaction tx;
    static UserPersistence persistence;

    @BeforeClass
    public static void liquibase() {
        persistence = new UserPersistence();
        persistence.em = DatabaseHelper.bootstrapLiquibaseJPA();
    }

    @Before
    public void init() {
        tx = persistence.em.getTransaction();
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
        Assert.assertNotNull(foundusers.get(0).getId());
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
