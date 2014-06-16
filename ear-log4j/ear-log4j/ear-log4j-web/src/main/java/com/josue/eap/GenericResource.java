/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.log4j.Logger;

@Path("generic")
@Stateless
public class GenericResource {

    static Logger logger = Logger.getLogger(GenericResource.class);
    @EJB
    private com.josue.eap.LogMeBean newSessionBean;

    public GenericResource() {
    }

    @GET
    @Produces("text/plain")
    public String getText(@QueryParam("query") @DefaultValue(value = "default") String query) {
        //for EAR Projects log4j.properties should be places under ear's META-INF folder

        logger.info("QUERY: " + query);
        newSessionBean.businessMethod();
        return "OK";
    }

}
