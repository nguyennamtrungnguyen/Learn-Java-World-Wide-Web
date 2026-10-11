
package vn.fit.nguyennamtrungnguyen.repository.impl;

import vn.fit.nguyennamtrungnguyen.model.DanhMuc;
import vn.fit.nguyennamtrungnguyen.dto.ThongKeDanhMucDTO;
import vn.fit.nguyennamtrungnguyen.repository.DanhMucRepository;
import vn.fit.nguyennamtrungnguyen.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */
public class DanhMucRepositoryImpl implements DanhMucRepository {

    private DanhMuc mapRow(ResultSet rs) throws SQLException {
        return new DanhMuc(
                rs.getInt("MADM"),
                rs.getString("TENDANHMUC"),
                rs.getString("NGUOIQUANLY"),
                rs.getString("GHICHU")
        );
    }

    // Lấy tất cả danh mục
    @Override
    public List<DanhMuc> findAll() throws SQLException {
        List<DanhMuc> danhMucList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM DANHMUC
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                danhMucList.add(mapRow(rs));
            }
        }

        return danhMucList;
    }

    // Tìm danh mục theo mã
    @Override
    public DanhMuc findById(int maDM) throws SQLException {
        String sql = """
                SELECT *
                FROM DANHMUC
                WHERE MADM = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDM);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }

        return null;
    }

    // Thêm danh mục
    @Override
    public boolean create(DanhMuc danhMuc) throws SQLException {
        String sql = """
                INSERT INTO DANHMUC
                    (TENDANHMUC, NGUOIQUANLY, GHICHU)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, danhMuc.getTENDANHMUC());
            ps.setString(2, danhMuc.getNGUOIQUANLY());
            ps.setString(3, danhMuc.getGHICHU());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    danhMuc.setMADM(rs.getInt(1));
                }
            }

            return true;
        }
    }

    // Cập nhật danh mục
    @Override
    public boolean update(DanhMuc danhMuc) throws SQLException {
        String sql = """
                UPDATE DANHMUC
                SET TENDANHMUC = ?,
                    NGUOIQUANLY = ?,
                    GHICHU = ?
                WHERE MADM = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, danhMuc.getTENDANHMUC());
            ps.setString(2, danhMuc.getNGUOIQUANLY());
            ps.setString(3, danhMuc.getGHICHU());
            ps.setInt(4, danhMuc.getMADM());

            return ps.executeUpdate() > 0;
        }
    }

    // Xóa danh mục theo mã
    @Override
    public boolean delete(int maDM) throws SQLException {
        String sql = """
                DELETE FROM DANHMUC
                WHERE MADM = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDM);

            return ps.executeUpdate() > 0;
        }
    }

//    Thống kê số lượng bài viết tin tức có trong mỗi danh mục
//    Câu lệnh sử dụng LEFT JOIN kết hợp GROUP BY và hàm COUNT() để đếm số
//    bài viết của từng danh mục (kể cả những danh mục chưa có bài viết nào).
    @Override
    public List<ThongKeDanhMucDTO> thongKeSoLuongTinTucTheoDanhMuc() throws SQLException {
        List<ThongKeDanhMucDTO> danhMucList = new ArrayList<>();

        String sql = """
                SELECT d.MADM, d.TENDANHMUC, COUNT(t.MATT) AS SOLUONG_TINTUC
                FROM DANHMUC d
                LEFT JOIN TINTUC t ON d.MADM = t.MADM
                GROUP BY d.MADM, d.TENDANHMUC
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                danhMucList.add(new ThongKeDanhMucDTO(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getLong("SOLUONG_TINTUC")
                ));
            }
        }

        return danhMucList;
    }

//    4. Liệt kê các danh mục có từ 3 bài viết tin tức trở lên
//    Câu lệnh này sử dụng HAVING để lọc kết quả sau khi đã gom nhóm (GROUPBY).
//    SELECT d.MADM, d.TENDANHMUC, COUNT(t.MATT) AS SOLUONG_TINTUC
//    FROM DANHMUC d
//    INNER JOIN TINTUC t ON d.MADM = t.MADM
//    GROUP BY d.MADM, d.TENDANHMUC
//    HAVING COUNT(t.MATT) >= 3;

    @Override
    public List<ThongKeDanhMucDTO> lietKeCacDanhMucSLTTTu3TroLen() throws SQLException {
        List<ThongKeDanhMucDTO> danhMucList = new ArrayList<>();

        String sql = """
                SELECT d.MADM, d.TENDANHMUC, COUNT(t.MATT) AS SOLUONG_TINTUC
                FROM DANHMUC d
                INNER JOIN TINTUC t ON d.MADM = t.MADM
                GROUP BY d.MADM, d.TENDANHMUC
                HAVING COUNT(t.MATT) >= 3
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                danhMucList.add(new ThongKeDanhMucDTO(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getLong("SOLUONG_TINTUC")
                ));
            }
        }

        return danhMucList;
    }
}
