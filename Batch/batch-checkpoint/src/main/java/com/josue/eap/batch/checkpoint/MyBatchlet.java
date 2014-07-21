/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint;

import java.util.logging.Logger;
import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class MyBatchlet extends AbstractBatchlet {

    private static final Logger LOG = Logger.getLogger(MyBatchlet.class.getName());

    @Override
    public String process() throws Exception {
        LOG.info("Running iniside Batchlet");

        return BatchStatus.COMPLETED.toString();
    }

}
