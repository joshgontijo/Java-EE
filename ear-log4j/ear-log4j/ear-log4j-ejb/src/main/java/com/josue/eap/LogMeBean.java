/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class LogMeBean {

    static Logger logger = Logger.getLogger(LogMeBean.class);
//    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(NewSessionBean.class.getName());

    public void businessMethod() {
        logger.info("INFO");
        logger.error("ERROR");

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
