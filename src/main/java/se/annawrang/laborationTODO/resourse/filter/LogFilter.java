package se.annawrang.laborationTODO.resourse.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(1)
public class LogFilter implements ContainerRequestFilter{
    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String log = String.format("Absolute path: %s\nMethod: %s\n",
                context.getUriInfo().getAbsolutePath(),
                "tbc");
        System.out.println(log);
    }
}
