/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.dynamic.injection;

import java.util.logging.Logger;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author Josue
 */
public class SimpleService {

    /*
     Injection occurs when a component is created and initialized by CDI.
     There are many cases where this is not desirable. In those cases,
     programmatic lookup is available by injecting the corresponding
     javax.enterprise.inject.Instance for a bean.
     */
    private static final Logger LOG = Logger.getLogger(SimpleService.class.getName());

    //Selective injection
    @Inject
    Instance<String> instances;

    public void getInstances() {
        //here we can define wich one will be injectes
        for (String s : instances) {
            LOG.info(s);
        }

    }

}
