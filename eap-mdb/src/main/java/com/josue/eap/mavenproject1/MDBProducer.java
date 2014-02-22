/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.eap.mavenproject1;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Josue
 */
@Stateless
@LocalBean
public class MDBProducer {
    @Resource(lookup = "java:jboss/exported/jms/queue/test")
    private Queue queueTest;
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    

    public void sendJMSMessageToTest(Object messageData) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(queueTest);
        TextMessage textMessage = session.createTextMessage("Message");
        messageProducer.send(textMessage);
        connection.close();
    }
}
