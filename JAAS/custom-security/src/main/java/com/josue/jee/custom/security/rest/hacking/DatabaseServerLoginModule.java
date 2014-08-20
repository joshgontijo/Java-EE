package com.josue.jee.custom.security.rest.hacking;

import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;
import org.jboss.security.plugins.TransactionManagerLocator;

/**
 * A JDBC based login module that supports authentication and role mapping. It
 * is based on two logical tables:
 * <ul>
 * <li>Principals(PrincipalID text, Password text)
 * <li>Roles(PrincipalID text, Role text, RoleGroup text)
 * </ul>
 * <p>
 * LoginModule options:
 * <ul>
 * <li><em>dsJndiName</em>: The name of the DataSource of the database
 * containing the Principals, Roles tables
 * <li><em>principalsQuery</em>: The prepared statement query, equivalent to:
 * <pre>
 *    "select Password from Principals where PrincipalID=?"
 * </pre>
 * <li><em>rolesQuery</em>: The prepared statement query, equivalent to:
 * <pre>
 *    "select Role, RoleGroup from Roles where PrincipalID=?"
 * </pre>
 * </ul>
 *
 * @author <a href="mailto:on@ibis.odessa.ua">Oleg Nitz</a>
 * @author Scott.Stark@jboss.org
 * @version $Revision: 86122 $
 */
public class DatabaseServerLoginModule extends UsernamePasswordLoginModule {

    
    
    /**
     * The JNDI name of the DataSource to use
     */
    protected String dsJndiName;
    /**
     * The sql query to obtain the user password
     */
    protected String principalsQuery = "select Password from Principals where PrincipalID=?";
    /**
     * The sql query to obtain the user roles
     */
    protected String rolesQuery = "select Role, RoleGroup from Roles where PrincipalID=?";
    /**
     * Whether to suspend resume transactions during database operations
     */
    protected boolean suspendResume = true;

    protected String TX_MGR_JNDI_NAME = "java:/TransactionManager";

    protected TransactionManager tm = null;

    /**
     * Initialize this LoginModule.
     *
     * @param options - dsJndiName: The name of the DataSource of the database
     * containing the Principals, Roles tables principalsQuery: The prepared
     * statement query, equivalent to: "select Password from Principals where
     * PrincipalID=?" rolesQuery: The prepared statement query, equivalent to:
     * "select Role, RoleGroup from Roles where PrincipalID=?"
     */
    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {
        super.initialize(subject, callbackHandler, sharedState, options);
        dsJndiName = (String) options.get("dsJndiName");
        if (dsJndiName == null) {
            dsJndiName = "java:/DefaultDS";
        }
        Object tmp = options.get("principalsQuery");
        if (tmp != null) {
            principalsQuery = tmp.toString();
        }
        tmp = options.get("rolesQuery");
        if (tmp != null) {
            rolesQuery = tmp.toString();
        }
        tmp = options.get("suspendResume");
        if (tmp != null) {
            suspendResume = Boolean.valueOf(tmp.toString()).booleanValue();
        }
        if (log.isTraceEnabled()) {
            log.trace("DatabaseServerLoginModule, dsJndiName=" + dsJndiName);
            log.trace("principalsQuery=" + principalsQuery);
            log.trace("rolesQuery=" + rolesQuery);
            log.trace("suspendResume=" + suspendResume);
        }
        //Get the Transaction Manager JNDI Name
        String jname = (String) options.get("transactionManagerJndiName");
        if (jname != null) {
            this.TX_MGR_JNDI_NAME = jname;
        }

        try {
            if (this.suspendResume) {
                tm = this.getTransactionManager();
            }
        } catch (NamingException e) {
            throw new RuntimeException("Unable to get Transaction Manager", e);
        }
    }

    /**
     * Get the expected password for the current username available via the
     * getUsername() method. This is called from within the login() method after
     * the CallbackHandler has returned the username and candidate password.
     *
     * @return the valid password String
     */
    protected String getUsersPassword() throws LoginException {
        boolean trace = log.isTraceEnabled();
        String username = getUsername();
        String password = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Transaction tx = null;
        if (suspendResume) {
            //tx = TransactionDemarcationSupport.suspendAnyTransaction();
            try {
                if (tm == null) {
                    throw new IllegalStateException("Transaction Manager is null");
                }
                tx = tm.suspend();
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
            if (trace) {
                log.trace("suspendAnyTransaction");
            }
        }

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dsJndiName);
            conn = ds.getConnection();
            // Get the password
            if (trace) {
                log.trace("Excuting query: " + principalsQuery + ", with username: " + username);
            }
            ps = conn.prepareStatement(principalsQuery);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next() == false) {
                if (trace) {
                    log.trace("Query returned no matches from db");
                }
                throw new FailedLoginException("No matching username found in Principals");
            }

            password = rs.getString(1);
            password = convertRawPassword(password);
            if (trace) {
                log.trace("Obtained user password");
            }
        } catch (NamingException ex) {
            LoginException le = new LoginException("Error looking up DataSource from: " + dsJndiName);
            le.initCause(ex);
            throw le;
        } catch (SQLException ex) {
            LoginException le = new LoginException("Query failed");
            le.initCause(ex);
            throw le;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (suspendResume) {
                //TransactionDemarcationSupport.resumeAnyTransaction(tx);
                try {
                    tm.resume(tx);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (log.isTraceEnabled()) {
                    log.trace("resumeAnyTransaction");
                }
            }
        }
        return password;
    }

    /**
     * Execute the rolesQuery against the dsJndiName to obtain the roles for the
     * authenticated user.
     *
     * @return Group[] containing the sets of roles
     */
    protected Group[] getRoleSets() throws LoginException {
        String username = getUsername();
        if (log.isTraceEnabled()) {
            log.trace("getRoleSets using rolesQuery: " + rolesQuery + ", username: " + username);
        }
//        Group[] roleSets = org.jboss.security.auth.spi.Util.getRoleSets(username, dsJndiName, rolesQuery, this,
//                suspendResume);
        return new Group[]{};
    }

    /**
     * A hook to allow subclasses to convert a password from the database into a
     * plain text string or whatever form is used for matching against the user
     * input. It is called from within the getUsersPassword() method.
     *
     * @param rawPassword - the password as obtained from the database
     * @return the argument rawPassword
     */
    protected String convertRawPassword(String rawPassword) {
        return rawPassword;
    }

    protected TransactionManager getTransactionManager() throws NamingException {
        TransactionManagerLocator tml = new TransactionManagerLocator();
        return tml.getTM(this.TX_MGR_JNDI_NAME);
    }
}
