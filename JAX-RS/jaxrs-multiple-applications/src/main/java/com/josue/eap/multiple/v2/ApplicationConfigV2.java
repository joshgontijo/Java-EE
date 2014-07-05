/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.multiple.v2;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Josue
 */
@javax.ws.rs.ApplicationPath("v2")
public class ApplicationConfigV2 extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        //Removindg resource thats auto added by NB
        resources.remove(com.josue.eap.multiple.v1.UserResourceV1.class);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.josue.eap.multiple.v2.UserResourceV2.class);
        resources.add(com.josue.eap.multiple.v1.UserResourceV1.class);

    }

}
