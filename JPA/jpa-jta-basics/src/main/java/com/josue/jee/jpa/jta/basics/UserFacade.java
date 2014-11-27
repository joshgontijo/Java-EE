/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.jpa.jta.basics;

import com.josue.jee.jpa.jta.basics.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;

/**
 *
 * @author Josue
 */
@Stateless
public class UserFacade {

    private static final Logger LOG = Logger.getLogger(UserFacade.class.getName());
    
    @PersistenceContext(unitName = "MY-PU")
    private EntityManager em;
    
    @Resource(mappedName = "java:comp/TransactionSynchronizationRegistry")
    TransactionSynchronizationRegistry tRegistry;
    
    
    private void traceTransaction() {
        LOG.log(Level.INFO, " #### Transaction Status - METHOD: {0} - STATUS: {1} - ID: {2}",
                new String[]{Thread.currentThread().getStackTrace()[2].getMethodName(),
                    TSTATUS.values()[tRegistry.getTransactionStatus()].name(), tRegistry.getTransactionKey().toString()});
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void persistRequiresNew(User user) {
       em.persist(user);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User findRequiresNew(Object uuid) {
        traceTransaction();
        User user = em.find(User.class, uuid);
        if(!em.contains(user)){
            throw new RuntimeException("!UserFacade.em.contains(user)");
        }
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public User findNotSuported(Object uuid) {
        traceTransaction();
        User user = em.find(User.class, uuid);
        if(!em.contains(user)){
            throw new RuntimeException("!UserFacade.em.contains(user)");
        }
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User findRequired(Object uuid) {
        traceTransaction();
        User user = em.find(User.class, uuid);

        if(!em.contains(user)){
            throw new RuntimeException("!UserFacade.em.contains(user)");
        }
        return user;
    }
    
    
    private enum TSTATUS {

        STATUS_ACTIVE,
        STATUS_MARKED_ROLLBACK,
        STATUS_PREPARED,
        STATUS_COMMITTED,
        STATUS_ROLLEDBACK,
        STATUS_UNKNOWN,
        STATUS_NO_TRANSACTION,
        STATUS_PREPARING,
        STATUS_COMMITTING,
        STATUS_ROLLING_BACK
    }
}
