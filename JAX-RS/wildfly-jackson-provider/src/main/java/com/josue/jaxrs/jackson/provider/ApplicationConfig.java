/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jaxrs.jackson.provider;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author cit
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);

//        resources.add(com.josue.jaxrs.jackson2.provider.CustomJacksonProvider.class);
//        resources.add(com.josue.jaxrs.jackson2.provider.GenericResource.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.josue.jaxrs.jackson.provider.CustomJacksonProvider.class);
        resources.add(com.josue.jaxrs.jackson.provider.GenericResource.class);
//        resources.add(org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider.class);
//        resources.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
//        resources.add(org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper.class);
//        resources.add(org.codehaus.jackson.jaxrs.JsonParseExceptionMapper.class);
        resources.add(org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider.class);
        resources.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
        resources.add(org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper.class);
        resources.add(org.codehaus.jackson.jaxrs.JsonParseExceptionMapper.class);
    }
}
