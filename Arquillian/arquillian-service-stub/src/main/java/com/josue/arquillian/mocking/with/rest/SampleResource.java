/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.mocking.with.rest;

import com.josue.arquillian.mocking.with.service.i.IMockService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author cit
 */
@Path("generic")
public class SampleResource {

    @Inject
    IMockService service;

    @GET
    @Produces("application/json")
    public String getJson() {
        String response = service.doIt();
        return response;
    }

}
