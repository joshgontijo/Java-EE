/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.util.logging.Logger;
import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class Step1Batchlet extends AbstractBatchlet {

    private static final Logger logger = Logger.getLogger(Step1Batchlet.class.getName());

    @Override
    public String process() throws InterruptedException {
        logger.info(":: STEP 1 BATCHLET STARTED ::");
        Thread.sleep(2000);
        logger.info(":: STEP 1 BATCHLET FINISHED ::");
        return BatchStatus.COMPLETED.name();
    }

}
