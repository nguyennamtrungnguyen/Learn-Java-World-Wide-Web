package iuh.fit.ontaprestapi.controller;

import iuh.fit.ontaprestapi.model.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/15/2026
 */

@Path("/user")
public class UserController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUSer(){
        List<User> users = new ArrayList<>();

        users.add(new User("1", "user1", "Nguyen"));
        users.add(new User("2", "user1", "Nguyen"));
        users.add(new User("3", "user1", "Nguyen"));
        return Response.ok(users).build();
    }
}
