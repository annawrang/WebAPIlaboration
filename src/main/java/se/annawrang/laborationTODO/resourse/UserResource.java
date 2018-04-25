package se.annawrang.laborationTODO.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import se.annawrang.laborationTODO.data.User;
import se.annawrang.laborationTODO.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.*;
import static javax.ws.rs.core.Response.Status.*;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private UserService service;
    @Context
    private UriInfo uriInfo;


    @GET
    public Response getAllUsers(){
        List<User> users = service.getAllUsers();
        return Response.ok(users).build();

    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id){

        User user = service.getUser(id);
        if(user == null){
            return status(NOT_FOUND).build();
        }
        return ok(user).build();
    }

    @GET
    @Path("{id}/todos")
    public Response getUserTodos(@PathParam("id") Long id, @QueryParam("priority") @DefaultValue("0") int priority){
        User user = service.getUser(id);
        if(user == null) return Response.status(NOT_FOUND).build();
        if(priority == 0) return Response.ok(user.getTodos()).build();

            return Response.ok(user.getTodos()
                    .stream()
                    .filter(todo -> todo.getPriority() == priority)
                    .collect(Collectors.toList())).build();
    }

    @POST
    public Response saveUser(User user){
        if(validateUser(user)) {
            User savedUser = service.saveUser(user);
            return Response.status(CREATED).header("Location", uriInfo
                    .getAbsolutePathBuilder()
                    .path(savedUser.getId().toString())).build();
        }
        return Response.status(BAD_REQUEST).build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        User user = service.getUser(id);
        if (user == null){
            return Response.status(NOT_FOUND).build();
        }
        service.delete(user);
        return Response.status(NO_CONTENT).build();
    }

    private boolean validateUser(User user) {
        if(user.getFirstName() == null || user.getSurName() == null){
            return false;
        }
        return true;
    }
}
