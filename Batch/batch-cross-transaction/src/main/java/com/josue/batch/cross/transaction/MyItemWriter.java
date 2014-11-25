/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.cross.transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Josue
 */
@Named
public class MyItemWriter extends AbstractItemWriter {

    private static final Logger LOG = Logger.getLogger(MyItemWriter.class.getName());

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void writeItems(List<Object> items) throws Exception {
        LOG.log(Level.INFO, "Saving items... size: {0}", items.size());
        
        for(Object o : items){
            Person person = (Person)o;
            em.persist(person);
        }
    }

}
