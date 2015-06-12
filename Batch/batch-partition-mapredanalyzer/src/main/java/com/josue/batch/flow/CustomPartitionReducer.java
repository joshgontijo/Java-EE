/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionReducer;
import javax.inject.Named;

/**
 *
 * @author jgontijo
 */
@Named
public class CustomPartitionReducer implements PartitionReducer {

    private static final Logger logger = Logger.getLogger(CustomPartitionReducer.class.getName());

    @Override
    public void beginPartitionedStep() throws Exception {
        logger.info(":: beginPartitionedStep ::");
    }

    @Override
    public void beforePartitionedStepCompletion() throws Exception {
        logger.info(":: beforePartitionedStepCompletion ::");

    }

    @Override
    public void rollbackPartitionedStep() throws Exception {
        logger.info(":: rollbackPartitionedStep ::");
    }

    @Override
    public void afterPartitionedStepCompletion(PartitionStatus status) throws Exception {
        logger.log(Level.INFO, ":: afterPartitionedStepCompletion PARTITIONSTATUS={0} ::", status.name());
    }

}
