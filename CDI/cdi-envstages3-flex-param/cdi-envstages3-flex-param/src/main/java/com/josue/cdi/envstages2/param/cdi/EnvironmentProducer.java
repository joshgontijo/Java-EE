/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.envstages2.param.cdi;

import com.josue.cdi.envstages2.param.service.Service;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * @author Josue
 */
public class EnvironmentProducer {

    private static final Logger logger = Logger.getLogger(EnvironmentProducer.class.getName());

    @Any
    @Inject
    Instance<Object> serviceBeans;

    @Produces
    public Service serviceProvider(InjectionPoint ip) {
        return getEnvAwareBean(Service.class);
    }

    private <T> T getEnvAwareBean(Class<T> type){
        String stage = System.getProperty(Environment.ENV_KEY);

        if (stage == null || stage.isEmpty()) {
            stage = Environment.DEFAULT_ENV;
        }

        Instance<T> found = serviceBeans.select(type).select(new StageQualifier(stage));
        if (!found.isUnsatisfied() && !found.isAmbiguous()) {
            return found.get();
        }

        throw new RuntimeException(":: COULD NOT FOUND ENVIRONMENT AWARE BEAN FOR '" + stage + "', TRYING DEFAULT '" + Environment.DEFAULT_ENV + "'");
    }


    public static class StageQualifier
            extends AnnotationLiteral<Environment>
            implements Environment {
        private String value;

        public StageQualifier(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}

