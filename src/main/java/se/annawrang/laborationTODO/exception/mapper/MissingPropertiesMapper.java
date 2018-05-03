package se.annawrang.laborationTODO.exception.mapper;

import se.annawrang.laborationTODO.exception.MissingPropertyException;
import se.annawrang.laborationTODO.resourse.filter.Logging;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Collections;

import static java.util.Collections.*;
import static javax.ws.rs.core.Response.*;
import static javax.ws.rs.core.Response.Status.*;

@Provider
@Logging
public class MissingPropertiesMapper implements ExceptionMapper<MissingPropertyException> {

    @Override
    public Response toResponse(MissingPropertyException e) {
        return Response.status(BAD_REQUEST).entity(singletonMap("error", e.getMessage())).build();
    }
}
