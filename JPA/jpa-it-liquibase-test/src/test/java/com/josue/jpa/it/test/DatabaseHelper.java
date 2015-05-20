/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.it.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 *
 * @author Josue
 */
@ApplicationScoped
public class DatabaseHelper {

    @Resource(lookup = "java:jboss/datasources/kingdom-testDS")
//    private DataSource datasource;
//    private static final Logger LOG = Logger.getLogger(DatabaseHelper.class.getName());

    public static void runLiquibase(Properties props) {

        Connection connection = null;
        try {
            String driver = props.getProperty("javax.persistence.jdbc.driver");
            String url = props.getProperty("javax.persistence.jdbc.url");
            String user = props.getProperty("javax.persistence.jdbc.user");
            String password = props.getProperty("javax.persistence.jdbc.password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("liquibase/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts());
        } catch (LiquibaseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public static EntityManager bootstrapLiquibaseJPA() {
        try {
            Properties props = new Properties();
            props.load(DatabaseHelper.class.getClassLoader().getResourceAsStream("persistence.properties"));
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("TEST-PU", props);
            EntityManager em = factory.createEntityManager();
            DatabaseHelper.runLiquibase(props);
            return em;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //NOT USED
//    public void cleanDatabase() {
//
//        LOG.log(Level.INFO, "### REMOVING DATABASE DATA ###");
//        try {
//            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(datasource.getConnection()));
//            Liquibase liquibase = new Liquibase("initial-test-data.xml", new FileSystemResourceAccessor(), database);
//            liquibase.update(new Contexts());
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (LiquibaseException ex) {
//            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        LOG.log(Level.INFO, "### DATABASE DATA SUCESSFUL REMOVED ###");
//    }
}
