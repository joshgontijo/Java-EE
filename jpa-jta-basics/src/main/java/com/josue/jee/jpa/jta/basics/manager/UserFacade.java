/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.jpa.jta.basics.manager;

import com.josue.jee.jpa.jta.basics.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Josue
 */
@Stateless(name = "uf")
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "MY-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

}
