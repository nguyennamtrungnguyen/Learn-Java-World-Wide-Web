package vn.fit.nguyennamtrungnguyen.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.nguyennamtrungnguyen.service.DepartmentService;

import java.math.BigDecimal;
import java.util.Map;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentController {

    private final DepartmentService departmentService = new DepartmentService();

    @GET
    public Response getAll() {
        return Response.ok(departmentService.findAll()).build();
    }

    /** Noi dung 5 - literal path, khai bao truoc /{id}. */
    @GET
    @Path("/statistics")
    public Response statistics() {
        return Response.ok(departmentService.getStatistics()).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getById(@PathParam("id") int id) {
        return departmentService.findById(id)
                .map(d -> Response.ok(d).build())
                .orElseGet(() -> notFound(id));
    }

    /** Noi dung 4. */
    @GET
    @Path("/{departmentId: \\d+}/employees")
    public Response getEmployees(@PathParam("departmentId") int departmentId,
                                 @QueryParam("minSalary") String minSalary,
                                 @QueryParam("maxSalary") String maxSalary) {
        BigDecimal min;
        BigDecimal max;
        try {
            min = parse(minSalary);
            max = parse(maxSalary);
        } catch (NumberFormatException ex) {
            return badRequest("minSalary/maxSalary phai la so");
        }
        try {
            return departmentService.findEmployeesByDepartment(departmentId, min, max)
                    .map(dto -> Response.ok(dto).build())
                    .orElseGet(() -> notFound(departmentId));
        } catch (IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }

    private static BigDecimal parse(String s) {
        return (s == null || s.isBlank()) ? null : new BigDecimal(s.trim());
    }

    private static Response notFound(int id) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("error", "Khong tim thay phong ban id = " + id))
                .build();
    }

    private static Response badRequest(String msg) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", msg))
                .build();
    }
}
