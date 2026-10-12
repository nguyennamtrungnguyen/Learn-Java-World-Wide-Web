package vn.fit.nguyennamtrungnguyen.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.service.EmployeeService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employees")
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    @GET
    public Response findAll() {
        return Response.ok(employeeService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("Message", "Khong tim thay nhan vien ban nhap!")).build();
        }
        return Response.ok(employee).build();
    }
    @GET
    @Path("/hire_date/{date}")
    public Response findByDate(@PathParam("date") String date) {
        Date hire_date = Date.valueOf(LocalDate.parse(date));
        return Response.ok(employeeService.findByDateHire(hire_date)).build();
    }

    @POST
    public Response create(Employee employee) {
        if (employeeService.save(employee)) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(Map.of("Message", "Them nhan vien moi thanh cong", "Ma nhan vien", employee.getId()))
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(Map.of("Message", "Them nhan vien moi that bai!"))
                .build();
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Employee employee) {
        employee.setId(id);
        if (employeeService.update(id, employee)) {
            return Response
                    .status(Response.Status.OK)
                    .entity(Map.of("Message", "Sua nhan vien thanh cong", "Ma nhan vien", employee.getId()))
                    .build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("Message", "Sua nhan vien that bai!"))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (employeeService.delete(id)) {
            return Response
                    .ok(Map.of("Message", "Xoa nhan vien thanh cong"))
                    .build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("Message", "Xoa nhan vien that bai!"))
                .build();
    }

}
