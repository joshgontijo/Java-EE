/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.AbstractBatchlet;
import javax.batch.api.BatchProperty;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class PartitionedBatchlet extends AbstractBatchlet {

    private static final Logger logger = Logger.getLogger(PartitionedBatchlet.class.getName());

    @Inject
    @BatchProperty(name = "start")
    private String startProp;

    @Inject
    @BatchProperty(name = "end")
    private String endProp;

    @Inject
    JobContext context;

    @Override
    public String process() throws InterruptedException {
        Properties properties = context.getProperties();
        logger.log(Level.INFO, ":: STARTED PARTITIONED BATCHLET >>> START={0} END={1} ::", new Object[]{startProp, endProp});
        int rand = new Random().nextInt(10 - 1) + 1;
        Thread.sleep(rand * 1000);
        logger.log(Level.INFO, ":: FINISHED PARTITIONED BATCHLET ::");

        return BatchStatus.COMPLETED.name();
    }

}
