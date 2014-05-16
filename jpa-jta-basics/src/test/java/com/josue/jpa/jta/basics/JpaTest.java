/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.jta.basics;

import com.josue.jee.jpa.jta.basics.entity.Address;
import com.josue.jee.jpa.jta.basics.entity.Order;
import com.josue.jee.jpa.jta.basics.entity.Phone;
import com.josue.jee.jpa.jta.basics.entity.User;
import com.josue.jee.jpa.jta.basics.manager.UserFacade;
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
import javax.transaction.TransactionSynchronizationRegistry;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
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

    @Resource
    TransactionSynchronizationRegistry tRegistry;

    @Deployment
    @TargetsContainer("wildfly-managed")
    public static WebArchive createDeployment() {

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "wildfly-test.war")
                .addPackage("com.josue.jee.jpa.jta.basics.entity")
                .addPackage("com.josue.jee.jpa.jta.basics.manager")
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
        LOG.log(Level.INFO, "Transaction Status - METHOD: {0} - STATUS: {1}",
                new String[]{Thread.currentThread().getStackTrace()[2].getMethodName(),
                    TSTATUS.values()[tRegistry.getTransactionStatus()].name()});
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testSimple() { //todo change name
        User user1 = createUser();
        userFacade.persist(user1);

        User foundUser = userFacade.find(user1.getId());
        LOG.info(user1.toString());
        LOG.info(foundUser.toString());

        //assertion for timestamp custom def
        Assert.assertEquals(user1.getDateCreated(), foundUser.getDateCreated());
        Assert.assertEquals(user1, foundUser);
    }
}
