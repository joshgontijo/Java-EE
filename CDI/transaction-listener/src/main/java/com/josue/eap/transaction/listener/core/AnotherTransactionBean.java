/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.eap.transaction.listener.core;

import com.josue.eap.transaction.listener.model.SampleUser;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;

/**
 *
 * @author Josue
 */


@Stateless
public class AnotherTransactionBean {
    private static final Logger LOG = Logger.getLogger(AnotherTransactionBean.class.getName());
    
    
    @Inject
    Event<String> txListeners;
    
    @Resource
    TransactionSynchronizationRegistry txReg;
    
    @PersistenceContext
    EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void sameTransactionContext(){
        LOG.log(Level.INFO, "Transaction: {0}", txReg.getTransactionKey());
        txListeners.fire(getClass().getSimpleName());
        
        SampleUser user = new SampleUser(UUID.randomUUID().toString());
        em.persist(user);
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void antoherTransactionContext(){
        LOG.log(Level.INFO, "Transaction: {0}", txReg.getTransactionKey());
        txListeners.fire(getClass().getSimpleName());
        
        SampleUser user = new SampleUser(UUID.randomUUID().toString());
        em.persist(user);
    }
    
}
