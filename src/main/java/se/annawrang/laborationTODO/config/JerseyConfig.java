package se.annawrang.laborationTODO.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.annawrang.laborationTODO.exception.mapper.MissingPropertiesMapper;
import se.annawrang.laborationTODO.resourse.TodoResource;
import se.annawrang.laborationTODO.resourse.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        register(MissingPropertiesMapper.class);
        register(UserResource.class);
        register(TodoResource.class);
    }
}
