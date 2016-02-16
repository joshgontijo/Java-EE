import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Josue on 16/02/2016.
 */
@Path("/lock")
@ApplicationScoped
public class CacheResource {

    private static final String LOCK = "LOCK-KEY";
    private HazelcastInstance hazelcastInstance;

    private static final Logger logger = Logger.getLogger(CacheResource.class.getName());

    //ref: http://docs.hazelcast.org/docs/latest/manual/html/lock.html

    @PostConstruct
    public void init() {
        hazelcastInstance = Hazelcast.newHazelcastInstance();
    }

    @GET
    @Path("simple")
    @Produces("text/plain")
    public String simpleLock() throws InterruptedException {

        ILock lock = hazelcastInstance.getLock(LOCK);
        logger.info("Trying to acquire lock");
        lock.lock();
        logger.info("Lock acquired");

        doWork(lock);

        return "OK";
    }

    @GET
    @Path("timeout")
    @Produces("text/plain")
    public String timeout(@QueryParam("timeout") @DefaultValue("2") Integer timeout) throws InterruptedException {

        ILock lock = hazelcastInstance.getLock(LOCK);
        logger.info("Trying to acquire lock with timeout: " + timeout + "s");
        if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
            logger.info("Lock acquired");
            doWork(lock);
        } else {
            logger.info("Lock not acquired within " + timeout + "s");
        }

        return "OK";
    }

    @GET
    @Path("lease")
    @Produces("text/plain")
    public String leaseTime(@QueryParam("lease") @DefaultValue("2") Integer lease) throws InterruptedException {

        ILock lock = hazelcastInstance.getLock(LOCK);
        logger.info("Trying to acquire lock");
        lock.lock(2, TimeUnit.SECONDS);
        logger.info("Lock acquired, it will be release in: " + lease + "s");

        logger.info(":: EXECUTING SOMETHING FOR 5s ::");
        Thread.sleep(5000);
        logger.info(":: DONE ::");

        try {
            lock.unlock();
        } catch (IllegalMonitorStateException ex) {
            logger.warning(":: Lock already released :: " + ex.getMessage());
        }


        return "OK";
    }

    private void doWork(ILock lock) throws InterruptedException {
        try {
            logger.info(":: EXECUTING SOMETHING FOR 5s ::");
            Thread.sleep(5000);
            logger.info(":: DONE ::");
        } finally {
            logger.info("Realeasing lock");
            lock.unlock();
            logger.info("Released");
        }
    }

}
