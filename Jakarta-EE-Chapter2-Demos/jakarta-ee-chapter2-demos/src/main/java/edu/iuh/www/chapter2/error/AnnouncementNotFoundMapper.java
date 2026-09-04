package edu.iuh.www.chapter2.error;

import edu.iuh.www.chapter2.announcement.AnnouncementNotFoundException;
import edu.iuh.www.chapter2.context.RequestMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
@ApplicationScoped
public class AnnouncementNotFoundMapper implements ExceptionMapper<AnnouncementNotFoundException> {
    @Inject
    private RequestMetadata requestMetadata;

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(AnnouncementNotFoundException exception) {
        ApiProblem problem = new ApiProblem(
                404,
                "Announcement not found",
                exception.getMessage(),
                uriInfo.getRequestUri().toString(),
                "ANNOUNCEMENT_NOT_FOUND",
                requestMetadata.getRequestId(),
                List.of());
        return Response.status(Response.Status.NOT_FOUND).entity(problem).build();
    }
}

