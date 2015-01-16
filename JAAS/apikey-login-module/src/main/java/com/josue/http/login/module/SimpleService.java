/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module;

import com.josue.http.login.module.auth.APIRole;
import com.josue.http.login.module.auth.AuthManager;
import javax.inject.Inject;

/**
 *
 * @author Josue
 */
public class SimpleService {

    @Inject
    AuthManager manager;

    public String getInjectedPrincipal() {
        String docUuid = "uuid-group1";

        if (manager.authorize(docUuid, APIRole.Level.WRITE)) {
            return "Have READ access to group " + docUuid;
        } else {
            return "DOESNT have READ access to group " + docUuid;
        }
    }

}
