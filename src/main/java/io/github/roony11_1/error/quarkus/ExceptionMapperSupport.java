package io.github.roony11_1.error.quarkus;

import org.slf4j.MDC;

import io.github.roony.error.core.ErrorHandler;
import io.github.roony.error.core.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

public abstract class ExceptionMapperSupport 
{
    @Context
    UriInfo uriInfo;

    protected ErrorResponse buildErrorResponse(Throwable exception) 
    {
        ErrorResponse response = ErrorHandler.toErrorResponse(exception);
        
        response.setPath(uriInfo != null ? uriInfo.getPath() : "desconocido");
        response.setTraceId(MDC.get("traceId"));

        return response;
    }
}
