/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.dynamic.database.externaldb.rest;

import com.josue.cdi.dynamic.database.externaldb.BMT;
import com.josue.cdi.dynamic.database.externaldb.Users;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Josue
 */
@Path("external")
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ExternalDatasourceRest {

    @Inject
    BMT bmt;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Users> migrate() {
        List<Users> foundUsers = bmt.fetchUsersFromDynamicDatasource();
        return foundUsers;
    }

}
