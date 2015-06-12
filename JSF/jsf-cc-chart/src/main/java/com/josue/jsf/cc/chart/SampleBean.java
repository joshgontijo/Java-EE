/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.cc.chart;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class SampleBean implements Serializable {

    private static final Logger logger = Logger.getLogger(SampleBean.class.getName());

    Random rand = new Random();

    public Integer updateRealtimeValue() {
        int realTimeXValue = rand.nextInt(30 - 1) + 1;
        logger.log(Level.INFO, "RANDON : {0}", realTimeXValue);
        return realTimeXValue;
    }
}
