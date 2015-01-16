package com.josue.http.login.module.util;

import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;
import javax.sql.DataSource;
import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class LiquibaseHelper {

    //TODO check running CDI twice
    @Resource(lookup = "java:jboss/datasources/testDS")
    private DataSource datasource;

    @Produces
    @LiquibaseType
    public CDILiquibaseConfig createConfig() {
        CDILiquibaseConfig config = new CDILiquibaseConfig();
        config.setChangeLog("liquibase/changelog.xml");
        return config;

    }

    @Produces
    @LiquibaseType
    public DataSource createDataSource() throws SQLException, NamingException {
        return datasource;
    }

    @Produces
    @LiquibaseType
    public ResourceAccessor create() {
        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }

}
