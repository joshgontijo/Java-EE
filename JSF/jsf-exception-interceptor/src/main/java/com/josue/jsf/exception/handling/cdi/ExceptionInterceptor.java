/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.exception.handling.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Josue
 */
@Interceptor
@ExceptionHandled
public class ExceptionInterceptor {

    private static final Logger logger = Logger.getLogger(ExceptionInterceptor.class.getName());

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) {
        try {
            return ctx.proceed();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error: {0}", ex.getMessage());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sumary", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panel");
            return null;
        }
    }
}
