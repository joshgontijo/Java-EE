/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.dynamic.database.externaldb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author iFood
 */
@ApplicationScoped
public class InMemoryDbProperties implements Serializable {

    private static final Logger LOG = Logger.getLogger(InMemoryDbProperties.class.getName());

    private Properties props = new Properties();

    public InMemoryDbProperties() {
        LOG.info("Initializing InMemoryDbProperties");
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("persistence.properties"));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

    }

    public void saveProps(String key, String value) {
        props.put(key, value);
    }

    public Properties loadProps() {
        return props;
    }

    public Object findProp(String key) {
        return props.get(key);
    }

}
