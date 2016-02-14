package com.josue.jms.simple.rest;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.*;

/**
 * Created by Josue on 13/02/2016.
 */
@Path("/sync")
@ApplicationScoped
public class JMSSyncResource {

//    <jms-queue name="testQueue" entries="queue/myqueue java:/jms/queue/myqueue"/>
//    <jms-queue name="testQueue2" entries="queue/myqueue2 java:/jms/queue/myqueue2"/>


    //*********** SENDER / CONSUMER ****************
    @Resource(lookup = "queue/myqueue2")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @GET
    @Produces("text/plain")
    public String consumeMessageSync() {
        JMSConsumer consumer = jmsContext.createConsumer(queue);
        String message = consumer.receiveBody(String.class, 1000);
        if (message == null) {
            message = "No message within 1sec";
        }
        consumer.close();
        return message;
    }

    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public String sendMessage(String message) {
        jmsContext.createProducer().send(queue, message);
        return "Message sent";
    }
}
