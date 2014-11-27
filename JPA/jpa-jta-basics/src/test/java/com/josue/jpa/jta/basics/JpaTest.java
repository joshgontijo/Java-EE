/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.jta.basics;

import com.josue.jee.jpa.jta.basics.UserFacade;
import com.josue.jee.jpa.jta.basics.entity.Address;
import com.josue.jee.jpa.jta.basics.entity.Order;
import com.josue.jee.jpa.jta.basics.entity.Phone;
import com.josue.jee.jpa.jta.basics.entity.User;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Josue
 */
@RunWith(Arquillian.class)
//@Transactional(TransactionMode.COMMIT)
public class JpaTest {

    private static final Logger LOG = Logger.getLogger(JpaTest.class.getName());

    @EJB(name = "uf")
    private UserFacade userFacade;

    @Resource(mappedName = "java:comp/TransactionSynchronizationRegistry")
    TransactionSynchronizationRegistry tRegistry;

    @PersistenceContext
    EntityManager em;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "wildfly-test.war")
                .addPackages(true, "com.josue.jee.jpa.jta.basics")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml",
                        "META-INF/persistence.xml")
                .addAsWebInfResource("mysql-test-ds.xml");

        return war;
    }

    private Address createAddress(User user) {
        Address address = new Address();
        address.setOwner(user);
        address.setStreet("Strreeet1");

        return address;
    }

    private Set<Phone> createPhones(User user, int numPhones) {

        Set<Phone> phones = new HashSet<>();

        for (int i = 0; i < numPhones; i++) {
            Phone p1 = new Phone();
            p1.setNumber(String.valueOf(new Random().nextInt() * 1000));
            p1.setOwner(user);
            phones.add(p1);
        }

        return phones;
    }

    private Set<Order> createOrders(List<User> users, int numPhones) {

        Set<Order> orders = new HashSet<>();

        for (int i = 0; i < numPhones; i++) {
            Order order = new Order();
            order.setOrderDate(new Date());
            order.setUser(new HashSet<>(users));
            orders.add(order);
        }
        return orders;
    }

    private User createUser() {
        User user1 = new User();
        user1.setName("josue");
        user1.setAddress(createAddress(user1));
        user1.setDateCreated(new Date());
        user1.setPhones(createPhones(user1, 3));
        user1.setOrders(createOrders(Arrays.asList(user1), 3));
        return user1;
    }

    private enum TSTATUS {

        STATUS_ACTIVE,
        STATUS_MARKED_ROLLBACK,
        STATUS_PREPARED,
        STATUS_COMMITTED,
        STATUS_ROLLEDBACK,
        STATUS_UNKNOWN,
        STATUS_NO_TRANSACTION,
        STATUS_PREPARING,
        STATUS_COMMITTING,
        STATUS_ROLLING_BACK
    }

    private void traceTransaction() {
        LOG.log(Level.INFO, " #### Transaction Status - METHOD: {0} - STATUS: {1} - ID: {2}",
                new String[]{Thread.currentThread().getStackTrace()[2].getMethodName(),
                    TSTATUS.values()[tRegistry.getTransactionStatus()].name(), tRegistry.getTransactionKey().toString()});
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testSimple() { //todo change name
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);

        User foundUser = userFacade.findRequired(user1.getId());
        traceTransaction();
        
        //assertion for timestamp custom def
//        Assert.assertEquals(user1.getDateCreated(), foundUser.getDateCreated());
        assertNotNull(foundUser);
        assertEquals(user1, foundUser);
        assertTrue(em.contains(foundUser));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    public void testReadOnlyQuery() {
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);

        User foundUser = userFacade.findNotSuported(user1.getId());
        traceTransaction();
        
        assertNotNull(foundUser);
        assertEquals(user1, foundUser);
        assertFalse(em.contains(foundUser));

    }
    
    @Test
    @Transactional(TransactionMode.DISABLED)
    public void testPersistWithoutJTA() {
        User user1 = createUser();
        em.persist(user1);

    }
    
    @Test
//    @Transactional(TransactionMode.DISABLED)
    public void testRequiresNew() {
        traceTransaction();
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);

        User foundUser = em.find(User.class, user1.getId());
        
        assertNotNull(foundUser);
        assertEquals(user1, foundUser);
        assertFalse(em.contains(foundUser));
        
        User foundUser2 = em.find(User.class, user1.getId());
        assertThat(foundUser, is(not(foundUser2)));

    }
    
    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testFindRequiresNew() {
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);
        assertFalse(em.contains(user1));
        
        User foundUser = userFacade.findRequiresNew(user1.getId());
        traceTransaction();
        
        assertNotNull(foundUser);
        assertEquals(user1, foundUser);
        assertFalse(em.contains(foundUser));

    }
    
    @Test
    @Transactional(TransactionMode.DISABLED)
    public void testRequired() {
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);
        
        User foundUser = userFacade.findRequiresNew(user1.getId());
        assertFalse(em.contains(foundUser));  
        assertFalse(em.contains(user1));
    }
    
    @Test
    @Transactional(TransactionMode.DISABLED)
    public void testJTAPropagation() {
        User user1 = createUser();
        userFacade.persistRequiresNew(user1);
        
        User foundUser = userFacade.findRequired(user1.getId());
        assertFalse("Should not contain outside the transaction", em.contains(user1));  
        assertFalse("Should not contain outside the transaction", em.contains(foundUser));      

    }
    
}
