/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.jsf.jaxrs.base64;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.commons.codec.binary.Base64;
/**
 * REST Web Service
 *
 * @author cit
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

   
    @GET
    @Produces("text/plain")
    public String getText(@QueryParam("query")String query) {
        byte[] decodedBytes = Base64.decodeBase64(query.getBytes());
        StringTokenizer token = new StringTokenizer(new String(decodedBytes),"&");
        Map<String, Object> params = new HashMap<>();
        while(token.hasMoreElements()){
            String keyVal = token.nextToken();
            params.put(keyVal.split("=")[0], keyVal.split("=")[1]);
        }
        
        for(Object o : params.values()){
            LOG.info(o.toString());
        }
        
        return new String(decodedBytes);
    }

}
