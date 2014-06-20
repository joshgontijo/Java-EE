/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs2.bean.param;

import javax.ws.rs.QueryParam;

/**
 *
 * @author Josue
 */
public class ParamWrapper {

    @QueryParam("val1")
    private String value1;
    @QueryParam("val2")
    private String value2;
    @QueryParam("val3")
    private String value3;
    @QueryParam("val4")
    private String value4;
    @QueryParam("val5")
    private String value5;

    @Override
    public String toString() {
        return "ParamWrapper{" + "value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + ", value4=" + value4 + ", value5=" + value5 + '}';
    }

}
