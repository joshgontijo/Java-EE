/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.subresources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
public class RootResource {

    @Context
    private UriInfo context;

    @Path("users")
    public Object getText() {
        return new SubResourceB();
    }

}
