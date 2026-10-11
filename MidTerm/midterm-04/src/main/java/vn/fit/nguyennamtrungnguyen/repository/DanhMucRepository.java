package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.dto.DanhMucTinTucDTO;
import vn.fit.nguyennamtrungnguyen.model.DanhMuc;
import vn.fit.nguyennamtrungnguyen.dto.ThongKeDanhMucDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public interface DanhMucRepository {
    // Lấy tất cả danh mục
    List<DanhMuc> findAll() throws SQLException;
    // Tìm danh mục theo mã
    DanhMuc findById(int maDM) throws SQLException;
    // Thêm danh mục
    boolean create(DanhMuc danhMuc) throws SQLException;
    // Cập nhật danh mục
    boolean update(DanhMuc danhMuc) throws SQLException;
    // Xóa danh mục theo mã
    boolean delete(int maDM) throws SQLException;
    List<ThongKeDanhMucDTO> thongKeSoLuongTinTucTheoDanhMuc() throws SQLException;
    List<ThongKeDanhMucDTO> lietKeCacDanhMucSLTTTu3TroLen() throws SQLException;
    List<ThongKeDanhMucDTO> findDanhMucCoNhieuTinTucNhat() throws SQLException;
    List<DanhMucTinTucDTO> findAllDanhMuc() throws SQLException;
}
