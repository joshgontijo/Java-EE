package com.josue.eap.rest.assured;

import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RestTest {

    UserRestClient client = new UserRestClient();

    private static final Logger LOG = Logger.getLogger(RestTest.class.getName());

    private User createUser(String uuid) {
        User user = new User();
        user.setAge(10);
        user.setName("abcde");

        String val = uuid == null ? UUID.randomUUID().toString() : uuid;
        user.setUuid(val);

        return user;
    }

    @Test
    public void testCreateUser() {
        User user = createUser(null);

        ClientResponse createResponse = client.createUser(user);
        User responseUser = createResponse.getEntity(User.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), createResponse.getStatus());
        assertNotNull(responseUser);
        assertNotEquals(user.getUuid(), responseUser.getUuid());

    }

    @Test
    public void testGetByUuid() {
        User user = createUser(null);

        User createdUser = client.createUser(user).getEntity(User.class);

        ClientResponse foundByUuidResp = client.getByUuid(createdUser.getUuid());
        assertEquals(Response.Status.OK.getStatusCode(), foundByUuidResp.getStatus());
        assertEquals(createdUser, foundByUuidResp.getEntity(User.class));

    }

    @Test
    public void testGetAll() {

        int count = 10;

        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User createdUser = client.createUser(createUser(null)).getEntity(User.class);
            users.add(createdUser);
        }

        ClientResponse allUsersResponse = client.getAll();

        assertEquals(Response.Status.OK.getStatusCode(), allUsersResponse.getStatus());
        List<User> foundUsers = allUsersResponse.getEntity(List.class);
        assertTrue(foundUsers.size() >= users.size());

    }

    @Test
    public void testUpdate() {
        User user = createUser(null);

        User created = client.createUser(user).getEntity(User.class);

        created.setName("TheNewName");
        ClientResponse response = client.update(created, created.getUuid());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(created, response.getEntity(User.class));

    }

    @Test
    public void testDelete() {
        User user = createUser(null);

        User created = client.createUser(user).getEntity(User.class);

        ClientResponse response = client.delete(created.getUuid());
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

}
