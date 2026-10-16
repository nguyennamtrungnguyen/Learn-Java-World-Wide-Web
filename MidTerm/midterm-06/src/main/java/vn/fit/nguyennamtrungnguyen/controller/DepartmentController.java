//package vn.fit.nguyennamtrungnguyen.controller;
//
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import vn.fit.nguyennamtrungnguyen.dto.DepartmentEmployeeDTO;
//import vn.fit.nguyennamtrungnguyen.service.DepartmentService;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
///**
// * @author Nguyễn Nam Trung Nguyên
// * @version 1.0
// * @MSSV 23640731
// * @Class DHKTPM19ATT
// * @since 10/12/2026
// */
//
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//@Path("/departments")
//public class DepartmentController {
//    private final DepartmentService departmentService = new DepartmentService();
////
////    @GET
////    @Path("/{departmentId}/employees")
////    public Response getEmployees(@PathParam("departmentId") int departmentId,
////                                 @QueryParam("minSalary") BigDecimal minSalary,
////                                 @QueryParam("maxSalary") BigDecimal maxSalary) {
////
////        DepartmentEmployeeDTO result =
////                departmentService.findEmployeesByDepartment(departmentId, minSalary, maxSalary);
////
////        if (result == null) {
////            return Response.status(404)
////                    .entity(Map.of("error", "Khong tim thay phong ban"))
////                    .build();
////        }
////        return Response.ok(result).build();
////    }
//
//    @GET
//    @Path("/statistics")
//    public Response getStatistics() {
//        return Response.ok(departmentService.getStatistics()).build();
//    }
//}
