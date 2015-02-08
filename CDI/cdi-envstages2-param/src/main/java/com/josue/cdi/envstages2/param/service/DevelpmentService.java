/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.envstages2.param.service;

import com.josue.cdi.envstages2.param.cdi.Development;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@Development
@ApplicationScoped
public class DevelpmentService implements Service {

    @Override
    public String doIt() {
        return "DEVELOPMENT SERVICE";
    }
}
