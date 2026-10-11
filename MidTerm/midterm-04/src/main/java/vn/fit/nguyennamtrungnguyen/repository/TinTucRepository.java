package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.dto.DanhMucTinTucDTO;
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
    boolean create(TinTuc tinTuc) throws SQLException;
    // Cập nhật tin tức
    boolean update(TinTuc tinTuc) throws SQLException;
    // Xóa tin tức theo mã
    boolean delete(int maTT) throws SQLException;
    List<DanhMucTinTucDTO> findAllWithNameDanhMuc() throws SQLException;
    List<DanhMucTinTucDTO> findAllWithNameDanhMucCuThe(String nameDanhMUC) throws SQLException;

    //    7. Lấy danh sách tin tức có tiêu đề chứa từ khóa "công nghệ"
    //    Câu lệnh kết hợp INNER JOIN với mệnh đề WHERE và toán tử LIKE để tìm
    //    các tin tức có tiêu đề chứa từ khóa cần tìm.
    List<DanhMucTinTucDTO> findAllCoTieuDeChuaTuKhoaCongNghe() throws SQLException;
}

