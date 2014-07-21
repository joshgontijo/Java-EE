/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.listener.JobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class InfoJobListener implements JobListener {

    private static final Logger LOG = Logger.getLogger(InfoJobListener.class.getName());

    @Inject
    private JobContext jobCtx;

    @Override
    public void beforeJob() throws Exception {
        LOG.log(Level.INFO, "Entering the job... name: {0}", new Object[]{jobCtx.getJobName()});
    }

    @Override
    public void afterJob() throws Exception {
        LOG.log(Level.INFO, "Exiting the job... name: {0}", new Object[]{jobCtx.getJobName()});
    }

}
