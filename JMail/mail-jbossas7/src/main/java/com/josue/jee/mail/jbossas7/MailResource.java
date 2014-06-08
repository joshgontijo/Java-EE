/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.mail.jbossas7;

import com.josue.jee.mail.jbossas7.sender.MailSender;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("mail")
@Stateless
public class MailResource {

    @EJB
    private MailSender mailSender;

    @Context
    private UriInfo context;

    @GET
    @Produces("text/plain")
    public String getText() {
        try {
            mailSender.sendMail("josue.eduardo206@gmail.com", "Subject", "value");
        } catch (NamingException ex) {
            Logger.getLogger(MailResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "OK";
    }

}
