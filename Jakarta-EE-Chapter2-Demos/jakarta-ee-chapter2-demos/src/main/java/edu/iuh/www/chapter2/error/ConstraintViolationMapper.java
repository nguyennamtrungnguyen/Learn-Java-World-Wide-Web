package edu.iuh.www.chapter2.error;

import edu.iuh.www.chapter2.context.RequestMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
@ApplicationScoped
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {
    @Inject
    private RequestMetadata requestMetadata;

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> violations = exception.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .sorted()
                .toList();
        ApiProblem problem = new ApiProblem(
                400,
                "Validation failed",
                "Dữ liệu gửi lên không thỏa hợp đồng API.",
                uriInfo.getRequestUri().toString(),
                "VALIDATION_FAILED",
                requestMetadata.getRequestId(),
                violations);
        return Response.status(Response.Status.BAD_REQUEST).entity(problem).build();
    }
}

