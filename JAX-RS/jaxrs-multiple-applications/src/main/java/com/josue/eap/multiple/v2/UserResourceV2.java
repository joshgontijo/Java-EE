/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.multiple.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("user")
public class UserResourceV2 {

    @GET
    @Produces("text/plain")
    public String getJson() {
        return "Multiple applications in same web project arent good ideia, anyway heres the V2";
    }

}
