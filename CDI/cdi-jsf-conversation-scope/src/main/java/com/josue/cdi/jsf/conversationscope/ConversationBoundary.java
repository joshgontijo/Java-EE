/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.jsf.conversationscope;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ConversationScoped
public class ConversationBoundary implements Serializable {

    private int counter;
    private static final Logger logger = Logger.getLogger(ConversationBoundary.class.getName());

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        logger.info("**!!!** POST CONSTRUCT **!!!**");
    }

    public void beginConversation() {
        if (conversation.isTransient()) {

            logger.info("*********** CONVERSATION STARTED ***************");
            conversation.begin();
        }
    }

    public String endConversation() {
        if (!conversation.isTransient()) {
            logger.info("*********** CONVERSATION ENDED... COUNTER SHOULD BE RESETED NEXT REQUEST ***************");
            conversation.end();
        }
        return "index.xhtml";
    }

    public void increment() {
        logger.log(Level.INFO, "** INCREMENT, ACTUAL VALUE: {0}", counter);
        counter += 1;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

}
