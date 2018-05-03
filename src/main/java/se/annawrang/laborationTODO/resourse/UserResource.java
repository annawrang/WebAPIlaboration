package se.annawrang.laborationTODO.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import se.annawrang.laborationTODO.data.Todo;
import se.annawrang.laborationTODO.data.User;
import se.annawrang.laborationTODO.exception.MissingPropertyException;
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

        if(doesUserExist(id)){
            User user = service.getUser(id);
            return ok(user).build();
        }
            return status(NOT_FOUND).build();
    }

    @GET
    @Path("{id}/todos")
    public Response getUserTodos(@PathParam("id") Long id, @QueryParam("priority") @DefaultValue("0") int priority){
        if(doesUserExist(id)){
            User user = service.getUser(id);
            if(priority == 0) return Response.ok(user.getTodos()).build();
            else {
                return Response.ok(user.getTodos()
                        .stream()
                        .filter(todo -> todo.getPriority() == priority)
                        .collect(Collectors.toList())).build();
            }
        }
        return Response.status(NOT_FOUND).build();
    }

    @POST
    public Response saveUser(User user){
        validateUser(user);
            user = service.saveUser(user);
            List<Todo> todos = user.getTodos();
            if(!(todos == null)){
                todos.forEach(t -> service.saveTodo(t));
            }
            return Response.status(CREATED).header("Location", uriInfo
                    .getAbsolutePathBuilder()
                    .path(user.getId().toString())).build();
    }

    @PUT
    @Path("{id}/todos/{todosId}")
    public Response addTodoToUser(@PathParam("id") Long id,
                                  @PathParam("todosId") Long todosId){
        User user = service.getUser(id);
        Todo todo = service.getTodo(todosId);

        if(user == null){
            return Response.status(NOT_FOUND).build();
        } else if(todo == null){
            return Response.status(NOT_FOUND).build();
        }
        user.getTodos().add(todo);
        service.saveUser(user);
        todo.setUser(user);
        service.saveTodo(todo);

        return Response.status(NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        if(doesUserExist(id)){
            User user = service.getUser(id);
            service.deleteUser(user);
            return Response.status(NO_CONTENT).build();
        }
            return Response.status(NOT_FOUND).build();
    }

    private void validateUser(User user) {
        if(user.getFirstName() == null || user.getSurName() == null){
            throw new MissingPropertyException("Missing property. You must provide first name and surname for User");
        }
    }

    private boolean doesUserExist(Long id){
        return service.getUser(id) != null;
    }
}
