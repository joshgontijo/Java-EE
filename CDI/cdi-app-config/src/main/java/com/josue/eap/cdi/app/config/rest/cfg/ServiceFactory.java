/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.app.config.rest.cfg;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Josue
 */
public class ServiceFactory {

    @Produces
    @Config
    public String getSampleString(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(Config.class).key();
        return "Sample String KEY: " + key;
    }

}
