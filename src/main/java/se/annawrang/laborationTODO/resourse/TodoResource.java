package se.annawrang.laborationTODO.resourse;

import org.glassfish.jersey.server.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.stereotype.Component;
import se.annawrang.laborationTODO.data.Todo;
import se.annawrang.laborationTODO.exception.MissingPropertyException;
import se.annawrang.laborationTODO.resourse.filter.Logging;
import se.annawrang.laborationTODO.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.util.List;

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
        Todo todo = service.getTodo(id);
        if(todo == null){
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(todo).build();
    }

    @GET
    public Response getAllTodos(){
        List<Todo> todos = service.getAllTodos();
        return Response.ok(todos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTodo(Todo todo){

        if(todo.getDescription() == null){
            // throw new MissingPropertyException(String.format("Missing property. You need to provide TODO description."));
        } else if(todo.getPriority() == 0){
            // throw new MissingPropertyException(String.format("Missing property. TODO priority must be set to O<."));
        }
        Todo savedTodo = service.saveTodo(todo);

        return Response.status(CREATED).header("Location",
                uriInfo.getAbsolutePathBuilder().path(savedTodo.getId().toString())).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTodo(@PathParam("id") Long id){
        Todo todo = service.getTodo(id);
        if(todo == null){
            return Response.status(NOT_FOUND).build();
        }
        service.deleteTodo(todo);
        return Response.status(NO_CONTENT).build();
    }
}
