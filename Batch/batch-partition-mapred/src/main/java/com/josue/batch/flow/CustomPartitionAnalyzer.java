/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionAnalyzer;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class CustomPartitionAnalyzer implements PartitionAnalyzer {

    private static final Logger logger = Logger.getLogger(CustomPartitionAnalyzer.class.getName());

    @Override
    public void analyzeCollectorData(Serializable data) throws Exception {
        logger.log(Level.INFO, ":: analyzeCollectorData {0} ::", data);
    }

    @Override
    public void analyzeStatus(BatchStatus batchStatus, String exitStatus) throws Exception {
        logger.log(Level.INFO, ":: analyzeStatus BATCHSTATUS {0} - EXITSTATUS {1} ::", new Object[]{batchStatus.name(), exitStatus});

    }

}
