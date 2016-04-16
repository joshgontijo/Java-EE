/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.envstages2.param.service;

import com.josue.cdi.envstages2.param.cdi.Environment;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@Environment("prod")
@ApplicationScoped
public class ProductionService implements Service {

    @Override
    public String doIt() {
        return "PRODUCTION SERVICE";
    }

}
