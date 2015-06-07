/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow.jsf;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.batch.runtime.StepExecution;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class BatchController implements Serializable {

    private static final Logger logger = Logger.getLogger(BatchController.class.getName());

    List<JobInstance> jobInstances;

    Map<JobInstance, List<JobExecution>> jobExecutions = new HashMap<>();
    Map<JobExecution, List<StepExecution>> stepExecutions = new HashMap<>();

    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
//        logger.info("LOADING...");
        try {
            jobInstances = BatchRuntime.getJobOperator().getJobInstances("partition-job", 0, 100);
        } catch (Exception e) {
            return;
        }
        Collections.sort(jobInstances, new Comparator<JobInstance>() {

            @Override
            public int compare(JobInstance o1, JobInstance o2) {
                return Long.valueOf(o2.getInstanceId()).compareTo(o1.getInstanceId());
            }
        });

        for (JobInstance jobInstance : jobInstances) {
            List<JobExecution> jobExecs = BatchRuntime.getJobOperator().getJobExecutions(jobInstance);
            jobExecutions.put(jobInstance, jobExecs);

            for (JobExecution jobExec : jobExecs) {
                List<StepExecution> steExecs = BatchRuntime.getJobOperator().getStepExecutions(jobExec.getExecutionId());
                stepExecutions.put(jobExec, steExecs);
            }
        }
    }

    public void startJob() {
        long execId = BatchRuntime.getJobOperator().start("simple-partition", null);
        logger.log(Level.INFO, ":: STARTED JOB: {0} ::", execId);

        load();
    }

    public void resumeJob(JobExecution jobExecution) {
        long newExecId = BatchRuntime.getJobOperator().restart(jobExecution.getExecutionId(), null);
        logger.log(Level.INFO, ":: RESUMED JOBEXECUTION: {0} - NEW ID: {1} ::", new Object[]{jobExecution.getExecutionId(), newExecId});
    }

    public void stopJob(JobExecution jobExecution) {
        if (jobExecution.getBatchStatus().equals(BatchStatus.STARTED)) {
            BatchRuntime.getJobOperator().stop(jobExecution.getExecutionId());
            logger.log(Level.INFO, ":: STOPPED JOBEXECUTION: {0} ::", jobExecution.getExecutionId());
        }
        logger.log(Level.INFO, ":: CANNOT STOP - JOB NOT RUNNING: {0} ::", jobExecution.getExecutionId());
    }

    public void stopAllJobs(JobInstance jobInstance) {
        logger.log(Level.INFO, "Stopping job {0}", jobInstance.getInstanceId());
        for (JobExecution jobExecution : jobExecutions.get(jobInstance)) {
            if (jobExecution.getBatchStatus().equals(BatchStatus.STARTED)) {
                BatchRuntime.getJobOperator().stop(jobExecution.getExecutionId());
            }
        }
    }

    public void abandonJob(JobExecution jobExecution) {
        if (jobExecution.getBatchStatus().equals(BatchStatus.STOPPED)) {
            BatchRuntime.getJobOperator().abandon(jobExecution.getExecutionId());
            logger.log(Level.INFO, ":: STOPED JOB: {0} ::", jobExecution.getExecutionId());
        }
        logger.log(Level.INFO, ":: CANNOT ABANDON - JOB NOT STOPPED: {0} ::", jobExecution.getExecutionId());
    }

    public List<JobInstance> getJobInstances() {
        return jobInstances;
    }

    public void setJobInstances(List<JobInstance> jobInstances) {
        this.jobInstances = jobInstances;
    }

    public Map<JobInstance, List<JobExecution>> getJobExecutions() {
        return jobExecutions;
    }

    public void setJobExecutions(Map<JobInstance, List<JobExecution>> jobExecutions) {
        this.jobExecutions = jobExecutions;
    }

    public Map<JobExecution, List<StepExecution>> getStepExecutions() {
        return stepExecutions;
    }

    public void setStepExecutions(Map<JobExecution, List<StepExecution>> stepExecutions) {
        this.stepExecutions = stepExecutions;
    }

}
