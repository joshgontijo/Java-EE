/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap;

import com.josue.eap.remote.lib.IRemote;
import javax.ejb.Stateless;

/**
 *
 * @author Josue
 */
@Stateless
public class NewSessionBean implements IRemote {

    @Override
    public String getMessage() {
        return "Remote !";
    }

}
