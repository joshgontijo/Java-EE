/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.annot.param.service;

import com.josue.cdi.annot.param.cdi.Environment;
import com.josue.cdi.annot.param.cdi.Stage;
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

        if (stageString == null) {
            stageString = "production";
        }
        Instance<Service> services = serviceBeans.select(new StageQualifier(stageString));
        if (!services.isUnsatisfied() && !services.isAmbiguous()) {
            return services.get();
        }
        throw new RuntimeException("Error while injecting bean");
    }

    public static class StageQualifier extends AnnotationLiteral<Environment> implements Environment {

        private final String value;

        public StageQualifier(String value) {
            this.value = value;
        }

        @Override
        public Stage stage() {
            return Stage.valueOf(value.toUpperCase());
        }
    }

}
