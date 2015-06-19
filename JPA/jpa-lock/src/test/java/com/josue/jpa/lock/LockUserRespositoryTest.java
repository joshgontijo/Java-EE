/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author jgontijo
 */
@RunWith(Arquillian.class)
public class LockUserRespositoryTest {

    private static final Logger logger = Logger.getLogger(LockUserRespositoryTest.class.getName());

    private volatile AssertionError assertionError;

    @PersistenceContext
    EntityManager em;

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService executor;

    @Inject
    LockUserRepository repository;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "lock-test.war")
                .addPackage(LockUser.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // 1- THREAD #1 READS THE DATA
    // 2- THREAD #2 READS THE DATA
    // 3- THREAD #2 UPDATE THE DATA ( UPDATE VERSION )
    // 4- THREAD #1 UPDATE THE DATA ( EXCEPTION )
    @Test
    public void testOptimisticLockException() throws Exception {
        final CountDownLatch testLatch = new CountDownLatch(2);
        final CountDownLatch threadsLatch = new CountDownLatch(1);

        LockUser lockUser = new LockUser("josue");
        repository.persist(lockUser);
        assertNotNull(lockUser.getId());
        final int userId = lockUser.getId();

        final ThreadExceptionWrapper exceptionWrapperThread1 = new ThreadExceptionWrapper();
        final ThreadExceptionWrapper exceptionWrapperThread2 = new ThreadExceptionWrapper();

        //THREAD #1
        executor.execute(new Runnable() {
            final Logger tLogger = Logger.getLogger(Thread.currentThread().getName());

            @Override
            public void run() {
                try {
                    LockUser foundUser = repository.optmisticLock(userId, LockModeType.OPTIMISTIC);
                    if (foundUser == null) {
                        throw new Exception("USER NOT FOUND");
                    }

                    tLogger.info(":: HANGING... ::");
                    threadsLatch.await();
                    tLogger.info(":: RELEASED... ::");

                    foundUser.setName("should not save");
                    repository.merge(foundUser);

                    testLatch.countDown();
                } catch (Exception ex) {
                    exceptionWrapperThread1.setException(ex);
                    tLogger.severe(ex.getMessage());
                } finally {
                    testLatch.countDown();
                }
            }
        });

        //THREAD #2
        executor.execute(new Runnable() {
            final Logger tLogger = Logger.getLogger(Thread.currentThread().getName());

            @Override
            public void run() {
                try {
                    LockUser foundUser = repository.optmisticLock(userId, LockModeType.OPTIMISTIC);
                    if (foundUser == null) {
                        throw new Exception("USER NOT FOUND");
                    }

                    foundUser.setName("name2");
                    repository.merge(foundUser);
                    tLogger.info(":: MERGED - VERSION SHOULD BE INCREMENTED ::");

                } catch (Exception ex) {
                    tLogger.severe(ex.getMessage());
                    exceptionWrapperThread2.setException(ex);
                } finally {
                    tLogger.info(":: THREADS LATCH... ::");
                    threadsLatch.countDown();
                    tLogger.info(":: TEST LATCH... ::");
                    testLatch.countDown();
                }
            }
        });

        logger.info("WAITING FOR TERMINATION");
        testLatch.await(10, TimeUnit.SECONDS);
        logger.info("TERMINATED");

        if (exceptionWrapperThread1.getException() != null) {
            Assert.assertEquals(OptimisticLockException.class, exceptionWrapperThread1.getException().getClass());
        } else {
            Assert.fail("SHOULD HAVE THROWN OPTIMISTICLOCKEXCEPTION");
        }

        if (exceptionWrapperThread2.getException() != null) {
            Assert.fail("SHOULDNT HAVE THROWED AN EXCEPTION... EX: " + exceptionWrapperThread2.getException().getMessage());
        }
    }

//    @Test
//    public void testOptimisticLockExceptionForceIncrement() throws Exception {
//        final CountDownLatch testLatch = new CountDownLatch(2);
//        final CountDownLatch threadsLatch = new CountDownLatch(1);
//
//        LockUser lockUser = new LockUser("josue");
//        repository.persist(lockUser);
//        assertNotNull(lockUser.getId());
//        final int userId = lockUser.getId();
//
//        final ThreadExceptionWrapper exceptionWrapperThread1 = new ThreadExceptionWrapper();
//        final ThreadExceptionWrapper exceptionWrapperThread2 = new ThreadExceptionWrapper();
//
//        //THREAD #1
//        executor.execute(new Runnable() {
//            final Logger tLogger = Logger.getLogger(Thread.currentThread().getName());
//
//            @Override
//            public void run() {
//                try {
//                    LockUser foundUser = repository.optmisticLock(userId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//                    if (foundUser == null) {
//                        throw new Exception("USER NOT FOUND");
//                    }
//                    if (foundUser.getVersion() != 1) {
//                        throw new Exception("VERSION SHOULD BE INCREMENTED");
//                    }
//
//                    tLogger.info(":: HANGING... ::");
//
//                    tLogger.info(":: RELEASED... ::");
//
//                    foundUser.setName("should not save");
//                    repository.merge(foundUser);
//
//                    testLatch.countDown();
//                } catch (Exception ex) {
//                    exceptionWrapperThread1.setException(ex);
//                    tLogger.severe(ex.getMessage());
//                } finally {
//                    testLatch.countDown();
//                }
//            }
//        });
//
//        //THREAD #2
//        executor.execute(new Runnable() {
//            final Logger tLogger = Logger.getLogger(Thread.currentThread().getName());
//
//            @Override
//            public void run() {
//                try {
//                    threadsLatch.await();
//                    LockUser foundUser = repository.optmisticLock(userId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//                    if (foundUser == null) {
//                        throw new Exception("USER NOT FOUND");
//                    }
//
//                    foundUser.setName("name2");
//                    repository.merge(foundUser);
//                    tLogger.info(":: MERGED - VERSION SHOULD BE INCREMENTED ::");
//
//                } catch (Exception ex) {
//                    tLogger.severe(ex.getMessage());
//                    exceptionWrapperThread2.setException(ex);
//                } finally {
//                    tLogger.info(":: THREADS LATCH... ::");
//                    threadsLatch.countDown();
//                    tLogger.info(":: TEST LATCH... ::");
//                    testLatch.countDown();
//                }
//            }
//        });
//
//        logger.info("WAITING FOR TERMINATION");
//        testLatch.await(10, TimeUnit.SECONDS);
//        logger.info("TERMINATED");
//
//        if (exceptionWrapperThread1.getException() != null) {
//            Assert.assertEquals(OptimisticLockException.class, exceptionWrapperThread1.getException().getClass());
//        } else {
//            Assert.fail("SHOULD HAVE THROWN OPTIMISTICLOCKEXCEPTION");
//        }
//
//        if (exceptionWrapperThread2.getException() != null) {
//            Assert.fail("SHOULDNT HAVE THROWED AN EXCEPTION... EX: " + exceptionWrapperThread2.getException().getMessage());
//        }
//    }
}
