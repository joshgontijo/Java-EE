/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.sample.multiple.datasources.Users;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.UriInfo;
import service.externaldb.BMT;

/**
 *
 * @author iFood
 */
@Stateless
@Path("com.sample.multiple.datasources.users")
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class UsersFacadeREST extends AbstractFacade<Users> {

    @Resource
    UriInfo uriInfo;

    @Resource
    TransactionSynchronizationRegistry tsr;

    @PersistenceContext(unitName = "com.sample_multiple-datasources_war_1.0PU")
    private EntityManager em;

    @Inject
    BMT bmt;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @GET
    @Path("migrate")
    @Produces({"application/xml", "application/json"})
    public List<Users> migrate() {
        List<Users> foundUsers = bmt.fetchUsersFromDynamicDatasource();
        for(Users u : foundUsers){
            em.merge(u);
        }
        return foundUsers;
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Users find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
