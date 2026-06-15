package io.github.roony11_1.error.quarkus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.roony11_1.error.core.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper extends ExceptionMapperSupport implements ExceptionMapper<Exception> 
{
    private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);
    
    @Override
    public Response toResponse(Exception exception) 
    {
        log.error("Error inesperado", exception);

        ErrorResponse body = buildErrorResponse(exception);
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(body).build();
    }
}