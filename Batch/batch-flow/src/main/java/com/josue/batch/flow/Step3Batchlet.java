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
public class Step3Batchlet extends AbstractBatchlet {

    private static final Logger logger = Logger.getLogger(Step3Batchlet.class.getName());

    @Override
    public String process() {
        logger.info(":: STEP 3 BATCHLET ::");
        return BatchStatus.COMPLETED.name();
    }

}
