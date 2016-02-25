package com.josue.cdi.sync;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.naming.InitialContext;
import java.util.logging.Logger;

/**
 * Created by Josue on 25/02/2016.
 */
public class SampleResourceIT extends Arquillian {

    @Inject
    SyncControl control;

    @ArquillianResource
    protected InitialContext initialContext;

    private static final Logger logger = Logger.getLogger(SampleResourceIT.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.josue.cdi.sync")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @BeforeSuite(groups = "arquillian")
    public void beforeSuite() {
    }


//    @Test(invocationCount = 5, threadPoolSize = 2) // bug: No TestRunnerAdaptor found, @BeforeSuite has not been called
    @Test(invocationCount = 2)
    public void testSynchronized(){
        int accessId = control.runSynchronized();
        logger.info(":: RESPONSE -> " + accessId + " ::");
        Assert.assertNotSame(accessId, 0);
    }
}