/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module.auth;

import java.security.Principal;
import java.util.Objects;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Josue
 */
@Alternative
public class APIRole implements Principal {

    public static enum Level {

        READ, WRITE, ADMIN
    }

    private final String role;

    public APIRole() {
        this.role = Level.READ.name();
    }

    public APIRole(Level role) {
        this.role = role.name();
    }

    public APIRole(String role) {
        Level level = Level.valueOf(role);
        this.role = level.name();
    }

    @Override
    public String getName() {
        return role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.role);
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
        final APIRole other = (APIRole) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }

}
