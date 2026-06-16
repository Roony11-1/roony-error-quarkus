package io.github.roony11_1.error.quarkus;

import io.github.roony11_1.error.core.ErrorResponse;
import io.github.roony11_1.error.core.exceptions.InvalidInputException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper extends ExceptionMapperSupport implements ExceptionMapper<ConstraintViolationException> 
{
    @Override
    public Response toResponse(ConstraintViolationException exception) 
    {
        String details = exception.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));

        InvalidInputException appEx = new InvalidInputException("Datos inválidos: " + details);

        ErrorResponse body = buildErrorResponse(appEx);
        return Response.status(Response.Status.BAD_REQUEST).entity(body).build();
    }
}