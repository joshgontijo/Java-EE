/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.dynamic.database;

import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author iFood
 */
@SessionScoped
public class InMemorySampleStorage implements Serializable {

    private Properties props;

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
