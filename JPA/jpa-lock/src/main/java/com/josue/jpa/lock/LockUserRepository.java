/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.lock;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author jgontijo
 */
public class LockUserRepository {

    private static final Logger logger = Logger.getLogger(LockUserRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public LockUser optmisticLock(int userId, LockModeType type) throws Exception {
        logger.log(Level.INFO, ":: RETRIVING LOCKUSER (ID {0}) USING OPTMISTIC LOCK ::", userId);
        return em.find(LockUser.class, userId, type);
    }
    
    @Transactional
    public void persist(LockUser lockUser) throws Exception {
        em.persist(lockUser);
    }

    @Transactional
    public LockUser merge(LockUser lockUser) throws Exception {
        return em.merge(lockUser);
    }

}
