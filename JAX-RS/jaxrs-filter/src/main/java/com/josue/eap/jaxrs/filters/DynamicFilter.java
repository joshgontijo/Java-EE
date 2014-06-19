/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.filters;

import java.util.logging.Logger;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Josue
 */
@Provider
public class DynamicFilter implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        LOG.info("DYNAMIC FEATURE CHECK...");
        if (resourceInfo.getResourceMethod().getAnnotation(javax.ws.rs.PUT.class) != null) {
            LOG.info("HEY A PUT METHOD... REGISTER A FILTER");
            context.register(SimpleFilter.class);
        }

    }
    private static final Logger LOG = Logger.getLogger(DynamicFilter.class.getName());

}
