/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module.auth;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 *
 * @author Josue
 */
public class APIKeyLoginModule implements LoginModule {

    private static final Logger LOG = Logger.getLogger(APIKeyLoginModule.class.getName());
    protected final String SERVLET_CONTEXT = "javax.servlet.http.HttpServletRequest";
    protected final String API_KEY = "ApiKey";

    private CallbackHandler handler;// not used
    private Subject subject;
    private Principal userPrincipal;
    private Principal rolePrincipal;
    private String login;
    private List<String> userGroups;

    private HttpServletRequest request;

    protected String dsJndiName = "java:jboss/datasources/testDS";

    protected String principalsQuery = "SELECT credential_uuid FROM api_credential where api_key = ?";
    protected String groupsQuery = "select group_uuid, role from credential_group_roles where credential_uuid = ?";
    protected String rolesQuery;

    protected boolean suspendResume = true;
    protected String txManagerJndiName = "java:/TransactionManager";

    @Override
    public void initialize(Subject subject,
            CallbackHandler callbackHandler,
            Map<String, ?> sharedState,
            Map<String, ?> options) {

        handler = callbackHandler;
        this.subject = subject;

        try {
            request = (HttpServletRequest) PolicyContext.getContext(SERVLET_CONTEXT);
        } catch (PolicyContextException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean login() throws LoginException {

        String requestApiKey = request.getHeader(API_KEY);
        if (requestApiKey == null || requestApiKey.isEmpty()) {
            throw new LoginException("Authentication failed");
        }

        String credential = getApiCredential(requestApiKey);
        LOG.log(Level.INFO, "Found API_KEY: {0}", credential);

        if (credential != null) {

            // We store the username and roles
            // fetched from the credentials provider
            // to be used later in commit() method.
            // For this tutorial we hard coded the
            // "admin" role
            login = credential;
            return true;
        }
        throw new LoginException("Authentication failed");
    }

    @Override
    public boolean commit() throws LoginException {

        //Simply return the credential
        Set<Principal> principals = subject.getPrincipals();
        APIPrincipal identity = new APIPrincipal(login);

        identity.getMembership().putAll(getGroupRoles(login));

        principals.add(identity);

        // Add role groups returned by getRoleSets.
//        for (Principal foundGroups : fetchPrincipalGroup(identity)) {
//            principals.add(foundGroups);
//        }
        return true;
    }

    //fetches groups from DB
    protected List<Principal> fetchPrincipalGroup(APIPrincipal principal) {

        //select * from groups where login = principal.getName();
        APIGroup foundGroup1 = new APIGroup("doc1-uuid");
        APIGroup foundGroup2 = new APIGroup("doc2-uuid");

        List<Principal> foundGroups = new ArrayList<>();
        foundGroups.add(foundGroup1);
        foundGroups.add(foundGroup2);

        return foundGroups;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }

    protected Map<Principal, APIRole> getGroupRoles(String credUuid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Principal, APIRole> groupRoles = new HashMap<>();
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dsJndiName);
            conn = ds.getConnection();

            ps = conn.prepareStatement(groupsQuery);
            ps.setString(1, credUuid);
            rs = ps.executeQuery();

            while (rs.next()) {
                String group = rs.getString("group_uuid");
                String roleName = rs.getString("role");

                groupRoles.put(new APIGroup(group), new APIRole(roleName));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(APIKeyLoginModule.class.getName()).log(Level.SEVERE, null, ex);
            return groupRoles;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return groupRoles;

    }

    protected String getApiCredential(String apiKey) throws LoginException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String credentialUuid = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dsJndiName);
            conn = ds.getConnection();

            ps = conn.prepareStatement(principalsQuery);
            ps.setString(1, apiKey);
            rs = ps.executeQuery();
            if (rs.next() == false) {
                return null;
            }

            credentialUuid = rs.getString(1);

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(APIKeyLoginModule.class
                    .getName()).log(Level.SEVERE, null, ex);
            return credentialUuid;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return credentialUuid;

    }

    String CALLER_RAI_IDENTIFIER = "CallerRunAsIdentity";
    String ROLES_IDENTIFIER = "Roles";
    String PRINCIPAL_IDENTIFIER = "Principal";
    String PRINCIPALS_SET_IDENTIFIER = "PrincipalsSet";
    String DEPLOYMENT_PRINCIPAL_ROLES_MAP = "deploymentPrincipalRolesMap";
    String SECURITY_CONTEXT = "SecurityContext";
    String CREDENTIAL = "Credential";
    String SUBJECT = "Subject";
    String JAVAEE = "JavaEE";
    String CALLER_PRINCIPAL_GROUP = "CallerPrincipal";

}
