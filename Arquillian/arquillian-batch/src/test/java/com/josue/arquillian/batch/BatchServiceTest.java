/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.batch;

import java.io.File;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.jboss.shrinkwrap.resolver.api.maven.Maven.resolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class BatchServiceTest {

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();
        File[] dependecies = resolver()
                .loadPomFromFile("pom.xml")
                .resolve("net.sf.opencsv:opencsv:2.3",
                        "commons-io:commons-io:2.4",
                        "org.liquibase:liquibase-cdi:3.1.1",
                        "org.apache.tomcat:tomcat-coyote:7.0.35",
                        "org.apache.commons:commons-lang3:3.3.2").withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "arquillian-batch.war")
                .addPackages(true, "com.josue.arquillian.batch")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                //                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .addAsResource("META-INF/batch-jobs")
                .addAsResource("META-INF/test-users.csv");
//                .addAsLibraries(libs);

        return war;
    }

    @Inject
    BatchService batchService;

    @Inject
    BatchRepository repository;

    @Test
    public void testStartJob() throws InterruptedException {
        int fileEntriesSize = 10;

        Assert.assertEquals(0, repository.getAllJobs().size());
        long jobId = batchService.startJob();

        int countLimit = 5;
        int count = 0;
        while (BatchRuntime.getJobOperator().getJobExecution(jobId).getBatchStatus() != BatchStatus.COMPLETED) {
            Thread.sleep(2000);
            count++;
            if (count > countLimit) {
                throw new RuntimeException("Countdown exceeded");
            }
        }

        Assert.assertEquals(1, repository.getAllJobs().size());
        Assert.assertEquals(fileEntriesSize, repository.getEntries(jobId).size());
    }

}
