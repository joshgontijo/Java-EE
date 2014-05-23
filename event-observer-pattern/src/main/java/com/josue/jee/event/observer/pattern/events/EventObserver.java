/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.event.observer.pattern.events;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author Josue
 */
@Stateless
public class EventObserver {

    private static final Logger LOG = Logger.getLogger(EventObserver.class.getName());

    public void listenToMyEvent(@Observes MyEvent myEvent) {
        LOG.log(Level.WARNING, "Hey... heres is fired the message #### {0} ####", myEvent.getMessage());
    }

}
