package io.github.roony11_1.error.quarkus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.roony11_1.error.core.ErrorResponse;
import io.github.roony11_1.error.core.exceptions.AppException;
import io.github.roony11_1.error.rest.HttpStatusRegistry;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper extends ExceptionMapperSupport implements ExceptionMapper<AppException> 
{
    private static final Logger log = LoggerFactory.getLogger(AppExceptionMapper.class);
    
    @Override
    public Response toResponse(AppException exception) 
    {
        log.warn("AppException: {} - {}", exception.getCode(), exception.getDisplayMessage());

        ErrorResponse body = buildErrorResponse(exception);
        int status = HttpStatusRegistry.getStatus(exception.getCategory());

        return Response.status(status).entity(body).build();
    }
}