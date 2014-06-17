/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.app.config.rest.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

@Singleton
@Startup
public class ServiceFactory {

    private Properties properties;
    private static final Logger log = Logger.getLogger(ServiceFactory.class.getName());

    @PostConstruct
    private void loadProperties() {
        try {
            log.info("Initializing properties...");
            InputStream inputStream = ServiceFactory.class.getClassLoader().getResourceAsStream("app.properties");
            this.properties = new Properties();
            this.properties.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ServiceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Produces
    @Config
    public String getStringValue(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(Config.class).key();
        String prop = properties.getProperty(key);
        return prop;
    }

    @Produces
    @Config
    public int getIntValue(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(Config.class).key();
        String prop = properties.getProperty(key);
        return Integer.parseInt(prop);
    }

    @Produces
    @Config
    public double getDoubleValue(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(Config.class).key();
        String prop = properties.getProperty(key);
        return Double.parseDouble(prop);
    }

}
