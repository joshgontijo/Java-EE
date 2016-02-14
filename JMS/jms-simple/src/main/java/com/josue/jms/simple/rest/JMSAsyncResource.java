package com.josue.jms.simple.rest;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Josue on 13/02/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/async")
@ApplicationScoped
public class JMSAsyncResource {

//    <jms-queue name="testQueue" entries="queue/myqueue java:/jms/queue/myqueue"/>
//    <jms-queue name="testQueue2" entries="queue/myqueue2 java:/jms/queue/myqueue2"/>

    @Resource(lookup = "queue/myqueue")
    private Queue queue;

    @Inject
//    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext jmsContext;

    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getMessage(String message) {
        jmsContext.createProducer().send(queue, message);
        return "Message sent";
    }
}
