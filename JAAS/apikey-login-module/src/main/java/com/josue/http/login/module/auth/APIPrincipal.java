/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Josue
 */
@Alternative
public class APIPrincipal implements Principal, Serializable {

    private String apiKey;
    private Map<Principal, APIRole> membership;

    public APIPrincipal() {
    }

    public APIPrincipal(String apiKey) {
        this.membership = new HashMap<>();
        this.apiKey = apiKey;
    }

    @Override
    public String getName() {
        return apiKey;
    }

    public Map<Principal, APIRole> getMembership() {
        return membership;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.apiKey);
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
        final APIPrincipal other = (APIPrincipal) obj;
        return Objects.equals(this.apiKey, other.apiKey);
    }

}
