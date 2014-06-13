/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.remote.lib;

import javax.ejb.Remote;

@Remote
public interface IRemoteUser {

    String getUser();
}
