package se.annawrang.laborationTODO.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import se.annawrang.laborationTODO.data.Todo;
import se.annawrang.laborationTODO.exception.MissingPropertyException;
import se.annawrang.laborationTODO.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.Response.Status.*;

@Path("todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Autowired
    private TodoService service;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Long id){
        if(doesTodoExist(id)){
            Todo todo = service.getTodo(id);
            return Response.ok(todo).build();
        }
            return Response.status(NOT_FOUND).build();
    }

    @GET
    public Response getAllTodos(){
        Iterable<Todo> todos = service.getAllTodos();
        return Response.ok(todos).build();
    }

    @POST
    public Response saveTodo(Todo todo){
        validateTodo(todo);
        todo = service.saveTodo(todo);

        return Response.status(CREATED).header("Location",
                uriInfo.getAbsolutePathBuilder().path(todo.getId().toString())).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTodo(@PathParam("id") Long id){
        if(doesTodoExist(id)){
            Todo todo = service.getTodo(id);
            service.deleteTodo(todo);
            return Response.status(NO_CONTENT).build();
        }
            return Response.status(NOT_FOUND).build();
    }

    private boolean doesTodoExist(Long id){
        return service.getTodo(id) != null;
    }

    private void validateTodo(Todo todo){
        if(todo.getDescription() == null){
             throw new MissingPropertyException(String.format("Missing property. You need to provide TODO description."));
        } else if(todo.getPriority() == 0){
             throw new MissingPropertyException(String.format("Missing property. TODO priority must be set to O<."));
        }
    }
}
