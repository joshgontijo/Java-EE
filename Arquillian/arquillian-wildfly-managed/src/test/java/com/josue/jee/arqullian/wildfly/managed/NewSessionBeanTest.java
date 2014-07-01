/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.arqullian.wildfly.managed;

import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class NewSessionBeanTest {

//    @EJB
//    private NewSessionBean sessionBean;
    //eap-lottery-ear-1.0-SNAPSHOT.ear
    @Deployment
    @TargetsContainer("wildfly-managed")
    public static EnterpriseArchive createDeployment() {

        //Load the target EAR
        //Testin
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "application-ear.ear")
                .as(ZipImporter.class)
                .importFrom(new File("eap-lottery-ear-1.0-SNAPSHOT.ear"))
                .as(EnterpriseArchive.class);

        //Add this class and other general libs to deployment
        JavaArchive testLibraryHelper = ShrinkWrap.create(JavaArchive.class)
                .addClass(NewSessionBeanTest.class)
                //now for CDI working in testLibraryHelper
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        ear.addAsLibrary(testLibraryHelper);

        return ear;
    }

    @Test
    public void testSessionBean() {

//        assertEquals(sessionBean.businessMethod(), "josue");
    }
}
