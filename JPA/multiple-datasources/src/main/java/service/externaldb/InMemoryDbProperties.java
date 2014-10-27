/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.externaldb;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;
import javax.inject.Singleton;

/**
 *
 * @author iFood
 */
@Singleton
public class InMemoryDbProperties implements Serializable {

    private static final Logger LOG = Logger.getLogger(InMemoryDbProperties.class.getName());
    
    private Properties props = new Properties();

    public InMemoryDbProperties() {
        LOG.info("Initializing InMemoryDbProperties");
    }
    
    public void saveProps(Properties newProp) {
        props = newProp;
    }
    
    public Properties loadProps() {
        return props;
    }
    
    public Object findProp(String key) {
        return props.get(key);
    }

}
