/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.multiple.v1;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Josue
 */
@javax.ws.rs.ApplicationPath("v1")
public class ApplicationConfigV1 extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        //Removindg resource thats auto created by NB
        resources.remove(com.josue.eap.multiple.v2.UserResourceV2.class);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.josue.eap.multiple.v1.UserResourceV1.class);
        resources.add(com.josue.eap.multiple.v2.UserResourceV2.class);
    }

}
