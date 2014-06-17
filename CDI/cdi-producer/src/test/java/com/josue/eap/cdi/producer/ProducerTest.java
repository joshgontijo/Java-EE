/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.producer;

import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class ProducerTest {

    @Inject
    String simpleString;

    @Inject
    @CustomKey(key = "josueCustomKey")
    String customString;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static JavaArchive createDeployment() {

        JavaArchive war = ShrinkWrap
                .create(JavaArchive.class, "wildfly-test.jar")
                .addPackage("com.josue.eap.cdi.producer")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    @Test
    public void testSessionBean() {

        Assert.assertNotNull(simpleString);

        Assert.assertNotNull(customString);
        Assert.assertEquals("josueCustomKey", customString);
    }

}
