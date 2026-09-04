package edu.iuh.www.chapter2.announcement;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/announcements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AnnouncementResource {
    @Inject
    private AnnouncementService service;

    @GET
    public List<Announcement> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Announcement findById(@PathParam("id") long id) {
        return service.findById(id);
    }

    @POST
    public Response create(@Valid CreateAnnouncementRequest request, @Context UriInfo uriInfo) {
        Announcement created = service.create(request);
        URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(created.id())).build();
        return Response.created(location).entity(created).build();
    }
}

