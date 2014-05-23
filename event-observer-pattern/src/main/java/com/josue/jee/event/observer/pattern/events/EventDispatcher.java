/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.event.observer.pattern.events;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Stateless
@Named("dispatcher")
public class EventDispatcher {

    private static final Logger LOG = Logger.getLogger(EventDispatcher.class.getName());

    @Inject
    private Event<MyEvent> events;

    public void doIt() {
        LOG.log(Level.INFO, "Firing the event");
        events.fire(new MyEvent("My Message"));
    }

}
