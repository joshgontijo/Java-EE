package com.josue.jms.simple;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

/**
 * Created by Josue on 13/02/2016.
 */
@MessageDriven(
        activationConfig = @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myqueue"))
public class JMSAsyncConsumer implements MessageListener {

    private static final Logger logger = Logger.getLogger(JMSAsyncConsumer.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            String payload = message.getBody(String.class);
            logger.info("Message received: " + payload);
        } catch (JMSException e) {
            logger.severe(e.getMessage());
        }
    }
}
