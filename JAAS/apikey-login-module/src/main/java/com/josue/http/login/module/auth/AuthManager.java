/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module.auth;

import com.josue.http.login.module.auth.APIRole.Level;
import java.security.Principal;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

/**
 *
 * @author Josue
 */
@RequestScoped
public class AuthManager {

    private static final Logger LOG = Logger.getLogger(AuthManager.class.getName());

    private final APIPrincipal principal;
    private APIPrincipal anonynous = new APIPrincipal("Anonymous");

    public AuthManager() {
        Subject caller = null;
        try {
            caller = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
        } catch (PolicyContextException ex) {
            LOG.log(java.util.logging.Level.SEVERE, "Could not get Subject from PolicyContext", ex);
        }
        if (caller == null) {
            principal = anonynous;
            return;
        }
        principal = extractPrincipal(caller);
    }

    public String getName() {
        return principal.getName();
    }

    public boolean isLoggedIn() {
        return principal != null && principal != anonynous;
    }

    public boolean authorize(String groupUuid, Level minLevel) {
        APIRole role = getAccessRole(groupUuid);
        if (role == null) {
            return false;
        } else {
            Level level = Level.valueOf(role.getName());
            if (level.compareTo(minLevel) >= 0) {
                return true;
            }
        }
        return false;
    }

    private APIRole getAccessRole(String groupUuid) {
        APIGroup group = new APIGroup(groupUuid);
        if (principal.getMembership().containsKey(group)) {
            return principal.getMembership().get(group);
        }
        return null;
    }

    private APIPrincipal extractPrincipal(Subject subject) {
        for (Principal princ : subject.getPrincipals()) {
            if (princ instanceof APIPrincipal) {
                return (APIPrincipal) princ;
            }
        }
        return anonynous;
    }

}
