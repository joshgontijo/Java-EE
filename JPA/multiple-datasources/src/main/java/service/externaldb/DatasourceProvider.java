/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.externaldb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


/**
 * @author Josue
 */

public class DatasourceProvider {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    @Inject
    InMemoryDbProperties properties;
    

    @Produces
    @CustomDatabase
    @RequestScoped
    public EntityManager datasource() throws NamingException {
        Properties props;
        LOG.info("GETTING DATASOURCE CONNECTION...");
        try {
//            props.load(getClass().getClassLoader().getResourceAsStream("persistence.properties"));
            props = properties.loadProps();
            if(props.size() == 0){
                props.load(getClass().getClassLoader().getResourceAsStream("persistence.properties"));
            }
            
            Map<String, String> map = new HashMap<String, String>();
            for (String key : props.stringPropertyNames()) {
                map.put(key, props.getProperty(key));
                if("hibernate.connection.url".equals(key)){
                    LOG.info("DATASOURCE URL: "+ props.getProperty(key));
                }
            }
            EntityManager em = emf.createEntityManager();
            return em;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Produces
    @CustomDatabase
    public Connection getDatabaseConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NamingException {

        try {
            Properties props;// = new Properties();
//            props.load(getClass().getClassLoader().getResourceAsStream("persistence.properties"));
            props = properties.loadProps();
            
            

            String url = props.getProperty("hibernate.connection.url");
            String user = props.getProperty("hibernate.connection.username");
            String driver = props.getProperty("javax.persistence.jdbc.driver");
            String password = props.getProperty("hibernate.connection.password");

            DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (Exception ex) {
            Logger.getLogger(DatasourceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Produces
    @CustomDatabase
    public Properties getPersistenceProperties() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("persistence.properties"));
        } catch (IOException ex) {
            Logger.getLogger(DatasourceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    private static final Logger LOG = Logger.getLogger(DatasourceProvider.class.getName());

}
