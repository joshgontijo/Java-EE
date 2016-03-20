import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Josue on 20/03/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/cache")
@ApplicationScoped
public class CacheResource {

    @Inject
    CachedControl control;

    @GET
    @Path("{key}")
    @Produces("text/plain")
    public String getMessage(@PathParam("key") String key) {
        return control.simpleCache(key);
    }

    @POST
    @Path("{key}")
    @Produces("text/plain")
    public Response addMessage(@PathParam("key") String key) {
        control.addToCache(key, key + " -> Sample message");
        return Response.created(null).build();
    }

    @DELETE
    @Produces("text/plain")
    public Response deleteAllMessages() {
        control.deleteAll();
        return Response.ok().build();
    }

    @DELETE
    @Path("{key}")
    @Produces("text/plain")
    public Response deleteMessage(@PathParam("key") String key) {
        control.delete(key);
        return Response.ok().build();
    }

}
