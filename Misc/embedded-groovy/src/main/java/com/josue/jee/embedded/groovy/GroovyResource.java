/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.embedded.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("groovy")
public class GroovyResource {

    @GET
    @Produces("text/plain")
    public String getText(@QueryParam("value") String value) {

        Binding groovyBinding = new Binding();
        groovyBinding.setVariable("theVal", value);
        GroovyShell groovyShell = new GroovyShell(groovyBinding);

        try {
            //All groovy script should catch an exception
            Object obj = groovyShell.evaluate("try {return 'From Groovy Script: ' + theVal} catch(RuntimeException e){return null}");
            return String.valueOf(obj);

        } catch (RuntimeException e) {
            return "Groovy compilation error: " + e.getMessage();
        }

    }

}
