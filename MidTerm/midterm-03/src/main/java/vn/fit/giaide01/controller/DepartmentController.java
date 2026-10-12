package vn.fit.giaide01.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.giaide01.service.DepartmentService;

import java.sql.SQLException;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;

    @GET
    public Response getAll() throws SQLException {
        return Response.ok(departmentService.findAll()).build();
    }
//
//    @GET
//    public Response getById() throws SQLException {
//        return Response.ok(departmentService.findAll()).build();
//    }

}
