/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.arqullian.wildfly.embedded;

import com.josue.jee.arquillian.wildfly.embedded.NewSessionBean;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class NewSessionBeanTest {

    @EJB
    private NewSessionBean sessionBean;

    @Deployment
    @TargetsContainer("wildfly-embedded")
    public static JavaArchive createDeployment() {

        JavaArchive war = ShrinkWrap
                .create(JavaArchive.class, "wildfly-test.jar")
                .addPackage(NewSessionBean.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    
    @Test
    public void testSessionBean1(){
        
        assertEquals(sessionBean.businessMethod(), "josue");
    }
    
    @Test
    public void testSessionBean2(){
        
        assertEquals(sessionBean.businessMethod(), "josue");
    }
    
    @Test
    public void testSessionBean3(){
        
        assertEquals(sessionBean.businessMethod(), "josue");
    }
}
