/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.batch;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;

/**
 *
 * @author Josue
 */
public class BatchService {

    private static final Logger LOG = Logger.getLogger(BatchService.class.getName());

    public long startJob() {
        JobOperator job = BatchRuntime.getJobOperator();
        long jid = job.start("myJob", new Properties());

        LOG.log(Level.INFO, "Started Job {0}", jid);
        return jid;
    }

}
