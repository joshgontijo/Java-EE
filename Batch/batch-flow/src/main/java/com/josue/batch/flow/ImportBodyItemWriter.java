/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;

/**
 *
 * @author Josue
 */
@Named
@ApplicationScoped
public class ImportBodyItemWriter extends AbstractItemWriter {

    private static final Logger logger = Logger.getLogger(ImportBodyItemWriter.class.getName());

    @Resource(mappedName = "java:comp/TransactionSynchronizationRegistry")
    TransactionSynchronizationRegistry registry;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        logger.log(Level.INFO, "SAVING VALUES SIZE: {0} - VALUES: {1}", new Object[]{items.size(), Arrays.toString(items.toArray())});
    }

}
