/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.subresources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Josue
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.remove(com.josue.eap.jaxrs.subresources.UserRest.class);
        resources.remove(com.josue.eap.jaxrs.subresources.AddressRest.class);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.josue.eap.jaxrs.subresources.DatabaseRest.class);
    }

}
