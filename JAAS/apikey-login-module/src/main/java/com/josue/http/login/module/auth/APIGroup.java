/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Josue
 */
@Alternative
public class APIGroup implements Principal, Serializable {

    //Default value... jboss specific impl, not used
    private static final String DEFAULT_ROLE_NAME = "Roles";

    //The document uuid
    private final String name;

    public APIGroup(String name) {
        this.name = name;
    }

    public APIGroup() {
        name = DEFAULT_ROLE_NAME;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIGroup other = (APIGroup) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
