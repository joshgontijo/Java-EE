/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.arqullian.wildfly.managed;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean {

    public String businessMethod() {
        
        return "josue";
    }


}
