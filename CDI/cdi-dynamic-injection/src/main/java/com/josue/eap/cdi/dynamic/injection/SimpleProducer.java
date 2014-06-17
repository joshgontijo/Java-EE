/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.dynamic.injection;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Josue
 */
public class SimpleProducer {

    @Produces
    public String produceStringA() {
        return "B";
    }

    @Produces
    public String produceStringB() {
        return "A";
    }

    @Produces
    @CustomKey
    public String produceCustomizedString(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(CustomKey.class).key();
        return "A custom String for KEY: " + key;
    }

}
