/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionCollector;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class CustomPartitionCollector implements PartitionCollector {

    private static final Logger logger = Logger.getLogger(CustomPartitionCollector.class.getName());

    @Override
    public Serializable collectPartitionData() throws Exception {
        String uuid = UUID.randomUUID().toString();
        logger.log(Level.INFO, "Returning UUID: {0}", uuid);
        return uuid;
    }

}
