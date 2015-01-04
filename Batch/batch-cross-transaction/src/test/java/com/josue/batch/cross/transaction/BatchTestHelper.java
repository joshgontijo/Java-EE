/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.cross.transaction;

import java.util.HashMap;
import java.util.Map;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.Metric;

/**
 * @author Roberto Cortez
 */
public final class BatchTestHelper {

    private static final int MAX_TRIES = 30;
    private static final int THREAD_SLEEP = 1000;

    private BatchTestHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * We need to keep the test running because JobOperator runs the batch job
     * in an asynchronous way, so the JobExecution can be properly updated with
     * the running job status.
     *
     * @param jobExecution the JobExecution of the job that is being runned on
     * JobOperator.
     *
     * @throws InterruptedException thrown by Thread.sleep.
     */
    public static void keepTestAlive(JobExecution jobExecution) throws InterruptedException {
        int maxTries = 0;
        while (!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
            if (maxTries < MAX_TRIES) {
                maxTries++;
                Thread.sleep(THREAD_SLEEP);
            } else {
                break;
            }
        }
    }

    /**
     * Convert the Metric array contained in StepExecution to a key-value map
     * for easy access to Metric parameters.
     *
     * @param metrics a Metric array contained in StepExecution.
     *
     * @return a map view of the metrics array.
     */
    public static Map<Metric.MetricType, Long> getMetricsMap(Metric[] metrics) {
        Map<Metric.MetricType, Long> metricsMap = new HashMap<>();
        for (Metric metric : metrics) {
            metricsMap.put(metric.getType(), metric.getValue());
        }
        return metricsMap;
    }
}
