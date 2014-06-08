/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.rest.apikey;

import com.josue.jee.rest.apikey.selector.ApiKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

// Do not use pre matching with @NameBinding annotation
//@PreMatching
@Provider
@ApiKey // filter only methods decorated with @ApiKey
public class ApiKeyFilter implements ContainerRequestFilter {

    private Map<String, Integer> counter = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(ApiKeyFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apikeyVal = requestContext.getHeaders().getFirst("api-key");
        LOG.info(apikeyVal);

    }

}
