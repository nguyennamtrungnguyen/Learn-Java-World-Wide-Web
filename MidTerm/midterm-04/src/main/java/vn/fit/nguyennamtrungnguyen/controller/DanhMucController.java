package vn.fit.nguyennamtrungnguyen.controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.nguyennamtrungnguyen.model.DanhMuc;
import vn.fit.nguyennamtrungnguyen.service.DanhMucService;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */

@Path("/danhmuc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DanhMucController {

    private final DanhMucService danhMucService = new DanhMucService();

    // Lấy tất cả danh mục
    @GET
    public Response getAll() throws SQLException {
        return Response
                .ok(danhMucService.findAll())
                .build();
    }

    // Lấy danh mục theo mã
    @GET
    @Path("/{id: [0-9]+}")
    public Response getById(@PathParam("id") int id) throws SQLException {
        DanhMuc danhMuc = danhMucService.findById(id);
        if (danhMuc == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "Danh muc not found"))
                    .build();
        }
        return Response
                .ok(danhMuc)
                .build();
    }

    // Thêm danh mục
    @POST
    public Response create(DanhMuc danhMuc) throws SQLException {
        if (danhMucService.create(danhMuc)) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(Map.of("message", "Danh muc created", "maDM", danhMuc.getMADM()))
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "Failed to create danh muc"))
                .build();
    }

    // Cập nhật danh mục
    @PUT
    @Path("/{id: [0-9]+}")
    public Response update(@PathParam("id") int id, DanhMuc danhMuc) throws SQLException {
        danhMuc.setMADM(id);
        if (danhMucService.update(danhMuc)) {
            return Response
                    .ok(Map.of("message", "Danh muc updated"))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Danh muc not found or not modified"))
                .build();
    }

    // Xóa danh mục theo mã
    @DELETE
    @Path("/{id: [0-9]+}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        if (danhMucService.delete(id)) {
            return Response
                    .ok(Map.of("message", "Danh muc deleted"))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Danh muc not found"))
                .build();
    }

    @GET
    @Path("thongketheosoluong")
    public Response getAllWithSoLuongTinTuc() throws SQLException {
        return Response
                .ok(danhMucService.thongKeSoLuongTinTucTheoDanhMuc())
                .build();
    }
}
