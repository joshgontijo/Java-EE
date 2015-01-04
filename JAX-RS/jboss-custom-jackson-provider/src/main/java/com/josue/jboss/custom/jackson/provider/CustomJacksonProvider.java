/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jboss.custom.jackson.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Josue
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJacksonProvider implements ContextResolver<ObjectMapper> {

    private static final Logger LOG = Logger.getLogger(CustomJacksonProvider.class.getName());

    private final ObjectMapper mapper;

    public CustomJacksonProvider() {
        LOG.info("********  CUSTOMJACKSONPROVIDER  ***********");
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        LOG.info("OBJECTMAPPERRESOLVER.GETCONTEXT(...)");
        return mapper;
    }
}
