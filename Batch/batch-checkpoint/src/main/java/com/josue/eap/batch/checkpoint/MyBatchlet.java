/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint;

import com.josue.eap.batch.checkpoint.tracker.Track;
import java.util.UUID;
import java.util.logging.Logger;
import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class MyBatchlet extends AbstractBatchlet {

    private static final Logger LOG = Logger.getLogger(MyBatchlet.class.getName());

    @Inject
    JobContext jobContext;
    
    @Override
    public String process() throws Exception {
        LOG.info("Running iniside Batchlet");

        jobContext.setTransientUserData(new Track(10, UUID.randomUUID().toString()));
        
        return BatchStatus.STARTED.toString();
    }

}
