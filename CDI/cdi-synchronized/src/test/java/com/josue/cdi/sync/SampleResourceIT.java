package com.josue.cdi.sync;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by Josue on 25/02/2016.
 */
public class SampleResourceIT extends Arquillian {

    @Inject
    SyncControl control;

    @Resource
    private ManagedExecutorService service;

    private static final Logger logger = Logger.getLogger(SampleResourceIT.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.josue.cdi.sync")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void testSynchronized(){

        TestWorker worker = new TestWorker();
        service.submit(worker);
        service.submit(worker);
        service.submit(worker);
        service.submit(worker);

        int accessId = control.runSynchronized();
        logger.info(":: RESPONSE -> " + accessId + " ::");
        Assert.assertNotSame(accessId, 0);
    }

    class TestWorker implements Runnable{

        @Override
        public void run() {
            control.runSynchronized();
        }
    }
}