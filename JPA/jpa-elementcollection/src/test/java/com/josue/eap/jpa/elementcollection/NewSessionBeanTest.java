package com.josue.eap.jpa.elementcollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class NewSessionBeanTest {

    @PersistenceContext(unitName = "com.josue.eap_jpa-elementcollection_war_1.0-SNAPSHOTPU")
    EntityManager em;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "wildfly-test.war")
                .addPackage("com.josue.eap.jpa.elementcollection")
                .addAsWebInfResource("jboss-external-ds.xml")
                .addAsResource("persistence.xml",
                        "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testSessionBean() {

        SimpleEntity se = new SimpleEntity();
        se.setIntegers(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)));
        se.setStrings(new HashSet<>(Arrays.asList("a", "b", "c")));
        em.persist(se);

        SimpleEntity foundSe = em.find(SimpleEntity.class, se.getId());

        Assert.assertNotNull(foundSe);
        Assert.assertEquals(se, foundSe);
    }
}
