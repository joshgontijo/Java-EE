/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.main;

import com.josue.eap.remote.lib.IRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@Stateless
public class GenericResource {

    @EJB(lookup = "java:jboss/exported/commons-ear-1.0-SNAPSHOT/commons-ejb-1.0-SNAPSHOT/NewSessionBean!com.josue.eap.remote.lib.IRemote")
    private IRemote newSessionBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        return newSessionBean.getMessage();
    }

}
