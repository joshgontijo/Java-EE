/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.mocking.with.rest;

import com.josue.arquillian.mocking.with.service.i.IMockService;
import javax.ejb.Stateless;

/**
 *
 * @author Josue
 */
@Stateless
public class SetviceTestStub implements IMockService {

    @Override
    public String doIt() {
        return "Mocked";
    }

}
