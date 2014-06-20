/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.mavenproject3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Josue
 */
//CLASS SHOULD BE ANNOTED EITH SOME SCOPE
@ApplicationScoped
public class NewClass {

    //Here is the injection, the provider is defined in the main project
    @Inject
    @InjectMe
    private String myString;

    //This is the ordinal simple way to use the injection =)
    //Check GenericResource.class FOR THE PRODUCER METHOD
    public String justReturn() {
        return myString;
    }

}
