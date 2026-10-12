package vn.fit.nguyennamtrungnguyen.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.service.EmployeeService;
import java.net.URI;
import java.util.Map;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    @GET
    public Response getAll() {
        return Response.ok(employeeService.findAll()).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getById(@PathParam("id") int id) {
        return employeeService.findById(id)
                .map(e -> Response.ok(e).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "Khong tim thay nhan vien id = " + id))
                        .build());
    }

    @POST
    public Response create(Employee employee) {
        try {
            Employee saved = employeeService.create(employee);
            return Response.created(URI.create("employees/" + saved.getId()))
                    .entity(saved)
                    .build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", ex.getMessage()))
                    .build();
        }
    }
}
