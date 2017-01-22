package com.josue.sse.jersey;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Josue on 22/01/2017.
 */
@Path("sse")
@ApplicationScoped
public class ServerSentEventsResource {

    private static final Logger logger = Logger.getLogger(ServerSentEventsResource.class.getName());

    private static final SseBroadcaster BROADCASTER = new SseBroadcaster();

    @Resource
    private ManagedScheduledExecutorService mses;

    @PostConstruct
    public void init() {
        mses.scheduleAtFixedRate(() -> {
            logger.info("Broadcasting message...");

            BROADCASTER.broadcast(new OutboundEvent.Builder()
                    .mediaType(MediaType.TEXT_PLAIN_TYPE)
                    .data(new SampleMessage(UUID.randomUUID().toString().substring(0, 8)))
                    .build());

        }, 1, 1, TimeUnit.SECONDS);
    }

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput broadcastEvent() throws IOException {
        EventOutput eventOutput = new EventOutput();
        BROADCASTER.add(eventOutput);
        return eventOutput;
    }


}
