/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jaxrs.jackson2.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJacksonProvider extends JacksonJsonProvider {

    private static final Logger log = Logger.getLogger(CustomJacksonProvider.class.getName());

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException {

        log.info("###################################################");
        ObjectMapper mapper = locateMapper(type, mediaType);

        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,
                false);

        super.writeTo(value, type, genericType, annotations, mediaType,
                httpHeaders, entityStream);
    }
}
