/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.envstages2.param.service;

import com.josue.cdi.envstages2.param.cdi.Development;
import com.josue.cdi.envstages2.param.cdi.Production;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

/**
 *
 * @author Josue
 */
public class EnvironmentProducer {

    @Inject
    @Any
    Instance<Service> serviceBeans;

    private static final String ENV = "env";

    @Produces
    public Service serviceProvider(InjectionPoint ip) {
        String stageString = System.getProperty(ENV);

        if ("production".equals(stageString) || stageString == null) {
            Instance<Service> services = serviceBeans.select(new AnnotationLiteral<Production>() {
            });
            return services.get();
        } else if ("development".equals(stageString)) {
            Instance<Service> services = serviceBeans.select(new AnnotationLiteral<Development>() {
            });
            return services.get();
        }

        throw new RuntimeException("Error while injecting bean");
    }

}
