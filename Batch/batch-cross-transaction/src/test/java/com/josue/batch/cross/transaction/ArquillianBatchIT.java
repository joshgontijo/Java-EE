/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.cross.transaction;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.Metric;
import javax.batch.runtime.StepExecution;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author iFood
 */
@RunWith(Arquillian.class)
public class ArquillianBatchIT {

    private static final Logger LOG = Logger.getLogger(ArquillianBatchIT.class.getName());
    
    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {


        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "test-batch.war")
                .addPackages(true, "com.josue.batch.cross.transaction")
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/batch-jobs")
                .addAsResource("test-fake-users.csv");

        return war;
    }

    @Test
    public void testProcessItem() throws InterruptedException {
         JobOperator jobOperator = BatchRuntime.getJobOperator();
        long executionId = jobOperator.start("myJob", null);
        LOG.log(Level.INFO, "Started job: {0}", executionId);

        BatchTestHelper.keepTestAlive(jobOperator.getJobExecution(executionId));
        LOG.log(Level.INFO, "Finished job: {0}", executionId);
        
        List<StepExecution> stepExecutions = jobOperator.getStepExecutions(executionId);
        for (StepExecution stepExecution : stepExecutions) {
            if ("mainStep".equals(stepExecution.getStepName())) {
                Map<Metric.MetricType, Long> metricsMap = BatchTestHelper.getMetricsMap(stepExecution.getMetrics());

                assertEquals(100, metricsMap.get(Metric.MetricType.READ_COUNT).intValue());
                // The write count should be 100.
                assertEquals(100, metricsMap.get(Metric.MetricType.WRITE_COUNT).intValue());
                // The commit count should be 11. Checkpoint is on every 10th read, plus one final read-commit.
                assertEquals(11, metricsMap.get(Metric.MetricType.COMMIT_COUNT).intValue());
            }
        }
        
    }
}
