/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.event.observer.pattern;

import com.josue.jee.event.observer.pattern.events.EventDispatcher;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("event")
@Stateless
public class EventResource {

    @EJB
    private EventDispatcher dispatcher;

    @GET
    @Produces("text/plain")
    public String getText() {
        dispatcher.doIt();
        return "OK";
    }

}
