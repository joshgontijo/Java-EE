/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.novapontocom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;



/**
 *
 * @author cit
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomJacksonProvider implements ContextResolver<ObjectMapper> {
    private static final Logger log = Logger.getLogger(CustomJacksonProvider.class.getName());
 
 
    @Override
    public ObjectMapper getContext(final Class<?> type) {
        final ObjectMapper mapper = new ObjectMapper();
 
        System.out.println("******************************************* ");
        log.info("Configured Jackson Json module");
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
 
        return mapper;
    }
}
