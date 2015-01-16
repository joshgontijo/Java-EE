///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.josue.http.login.module.auth;
//
//import com.josue.http.login.module.filter.Custom;
//import java.security.Principal;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Produces;
//import javax.security.auth.Subject;
//import javax.security.jacc.PolicyContext;
//import javax.security.jacc.PolicyContextException;
//
///**
// *
// * @author Josue
// */
//@ApplicationScoped
//public class APIPrincipalProducer {
//
//    private static final Logger LOG = Logger.getLogger(APIPrincipalProducer.class.getName());
//
//    @Produces
//    @Custom
//    @RequestScoped
//    public APIPrincipal produce() {
//
//        APIPrincipal principal;
//        Subject caller = null;
//        try {
//            caller = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
//        } catch (PolicyContextException ex) {
//            LOG.log(Level.SEVERE, "Could not get Subject from PolicyContext", ex);
//        }
//        if (caller == null) {
//            return anonymous();
//        }
//        principal = extractPrincipal(caller);
//
//        return principal;
//    }
//
//    private APIPrincipal extractPrincipal(Subject subject) {
//        for (Principal principal : subject.getPrincipals()) {
//            if (principal instanceof APIPrincipal) {
//                return (APIPrincipal) principal;
//            }
//        }
//        return anonymous();
//    }
//
//    private APIPrincipal anonymous() {
//        return new APIPrincipal("Anonymous");
//    }
//}
