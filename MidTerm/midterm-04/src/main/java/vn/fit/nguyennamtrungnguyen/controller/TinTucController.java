package vn.fit.nguyennamtrungnguyen.controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.nguyennamtrungnguyen.model.TinTuc;
import vn.fit.nguyennamtrungnguyen.service.TinTucService;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
@Path("/tintuc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TinTucController {
    private final TinTucService tinTucService = new TinTucService();

    // Lấy tất cả tin tức
    @GET
    public Response getAll() throws SQLException {
        return Response
                .ok(tinTucService.findAll())
                .build();
    }
    // Lấy tin tức theo mã
    @GET
    @Path("/{id: [0-9]+}")
    public Response getById(@PathParam("id") int id) throws SQLException {
        TinTuc tinTuc = tinTucService.findById(id);
        if (tinTuc == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "Tin tuc not found"))
                    .build();
        }
        return Response.ok(tinTuc).build();
    }

    // Thêm tin tức
    @POST
    public Response create(TinTuc tinTuc) throws SQLException {
        if (tinTucService.create(tinTuc)) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(Map.of("message", "Tin tuc created", "maTT", tinTuc.getMATT()))
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "Failed to create tin tuc"))
                .build();
    }

    // Cập nhật tin tức
    @PUT
    @Path("/{id: [0-9]+}")
    public Response update(@PathParam("id") int id, TinTuc tinTuc) throws SQLException {
        tinTuc.setMATT(id);
        if (tinTucService.update(tinTuc)) {
            return Response
                    .ok(Map.of("message", "Tin tuc updated"))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Tin tuc not found or not modified"))
                .build();
    }

    // Xóa tin tức theo mã
    @DELETE
    @Path("/{id: [0-9]+}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        if (tinTucService.delete(id)) {
            return Response
                    .ok(Map.of("message", "Tin tuc deleted"))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Tin tuc not found"))
                .build();
    }

    @GET
    @Path("/findAllWithNameDanhMuc")
    public Response getAllWithNameDanhMuc() throws SQLException {
        return Response
                .ok(tinTucService.findAllWithNameDanhMuc())
                .build();
    }
    @GET
    @Path("/findAllWithNameDanhMucCuThe/{nameDanhMuc}")
    public Response getAllWithNameDanhMuc(@PathParam("nameDanhMuc") String nameDanhMuc) throws SQLException {
        return Response
                .ok(tinTucService.findAllWithNameDanhMucCuThe(nameDanhMuc))
                .build();
    }
    @GET
    @Path("/findAllCoTieuDeChuaTuKhoaCongNghe")
    public Response getAllCoTieuDeChuaTuKhoaCongNghe() throws SQLException {
        return Response
                .ok(tinTucService.findAllCoTieuDeChuaTuKhoaCongNghe())
                .build();
    }
    @GET
    @Path("/lietKeCacTinTucThuocDanhMuc")
    public Response getCacTinTucThuocDanhMuc() throws SQLException {
        return Response
                .ok(tinTucService.lietKeCacTinTucThuocDanhMuc())
                .build();
    }
}
