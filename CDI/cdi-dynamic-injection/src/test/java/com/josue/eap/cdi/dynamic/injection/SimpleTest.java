package com.josue.eap.cdi.dynamic.injection;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SimpleTest {

    @Inject
    SimpleService simpleService;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static JavaArchive createDeployment() {

        JavaArchive war = ShrinkWrap
                .create(JavaArchive.class, "wildfly-test.jar")
                .addPackage("com.josue.eap.cdi.dynamic.injection")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    @Test
    public void testSessionBean() {
        simpleService.getInstances();
    }

}
