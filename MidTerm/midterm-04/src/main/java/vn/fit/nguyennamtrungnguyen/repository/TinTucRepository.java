package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.model.TinTuc;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/11/2026
 */
public interface TinTucRepository {
    // Lấy tất cả tin tức
    List<TinTuc> findAll() throws SQLException;

    // Tìm tin tức theo mã
    TinTuc findById(int maTT) throws SQLException;

    // Thêm tin tức
    boolean save(TinTuc tinTuc) throws SQLException;

    // Cập nhật tin tức
    boolean update(TinTuc tinTuc) throws SQLException;

    // Xóa tin tức theo mã
    boolean delete(int maTT) throws SQLException;
}
