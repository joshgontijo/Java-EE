/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionCollector;
import javax.inject.Named;

/**
 *
 * @author jgontijo
 */
@Named
public class CustomPartitionCollector implements PartitionCollector{

    private static final Logger logger = Logger.getLogger(CustomPartitionCollector.class.getName());

    @Override
    public Serializable collectPartitionData() throws Exception {
        logger.info(":: collectPartitionData SHOULD RETURN SERIALIZABLE ::");
        return "some-serializable-return-value";
    }
    
}
