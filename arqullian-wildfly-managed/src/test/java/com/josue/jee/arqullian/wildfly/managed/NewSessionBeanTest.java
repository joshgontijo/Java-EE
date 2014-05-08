/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.arqullian.wildfly.managed;

import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "wildfly-test.war")
                .addPackage(NewSessionBean.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    
    @Test
    public void testSessionBean(){
        
        assertEquals(sessionBean.businessMethod(), "josue");
    }
}
