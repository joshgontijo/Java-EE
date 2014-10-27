/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.dynamic.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * @author Josue
 */
@Path("users")
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class UserResource {

    @Inject
    @CustomDatabase
    private Connection connection;

    @Inject
    @CustomDatabase
    private EntityManager em;

    @Inject
    @CustomDatabase
    private Properties props;

    @GET
    @Path("/liquibase")
    @Produces(MediaType.APPLICATION_JSON)
    public void runLiquibase() throws LiquibaseException, SQLException, NamingException {

        Liquibase liquibase = null;
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        liquibase = new Liquibase("liquibase/changelog.xml", new ClassLoaderResourceAccessor(this.getClass().getClassLoader()), database);
        liquibase.update(new Contexts());
    }

    @POST
    @Path("/update-datasource")
    @Consumes(MediaType.TEXT_PLAIN)
    public void updateDatasource(String propString) throws LiquibaseException, SQLException, NamingException {

        Properties props = new Properties();
        for (String line : propString.split("\n")) {
            String[] keyValue = line.split(",");
            props.put(keyValue[0], keyValue[1]);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User saveUser(User user) {

        user = em.merge(user);
        return user;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUser() {

        TypedQuery<User> tq = em.createQuery("SELECT u FROM users_db u", User.class);
        List<User> users = tq.getResultList();
        return users;

    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("uuid") String uuid) {

        return em.find(User.class, uuid);

    }

    @GET
    @Path("properties/{prop}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProperty(@PathParam("prop") String prop) {
        String value = props.getProperty(prop);
        return value;

    }

    //
//    @PUT
//    @Path("{uuid}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public User updateUser(User user, @PathParam("uuid") String uuid) {
//        User foundUser = em.find(User.class, uuid);
//        if (foundUser != null) {
//            foundUser = user;
//        }
//        return foundUser;
//    }
//
//    @DELETE
//    @Path("{uuid}")
//    public void deleteUser(@PathParam("uuid") String uuid) {
//        User foundUser = em.find(User.class, uuid);
//        if (foundUser != null) {
//            em.remove(foundUser);
//        }
//    }
    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());
}
