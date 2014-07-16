package com.josue.arquillian.mocking.with.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.josue.arquillian.mocking.with.client.NewJerseyClient;
import com.josue.arquillian.mocking.with.service.i.IMockService;
import java.io.File;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.jboss.shrinkwrap.resolver.api.maven.Maven.resolver;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
public class SampleResourceTest {

    @Inject
    IMockService mocked;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        File[] dependecies = resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.mockito:mockito-all:1.9.5").withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "wildfly-test.war")
                //                .addPackage(MockMeService.class.getPackage())
                .addClass(IMockService.class)
                //Adding test impl for IMockService
                .addClass(SetviceTestStub.class)
                .addPackage(NewJerseyClient.class.getPackage())
                .addPackage(SampleResource.class.getPackage())
                .addAsLibraries(dependecies)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return war;
    }

    @Test
    public void testServiceWithMock() {

        NewJerseyClient client = new NewJerseyClient();
        String response = client.getJson();

        assertEquals("Mocked", mocked.doIt());// dumb call to mocked service
        assertEquals("Mocked", response); // here its the real use of stub objects
    }

}
