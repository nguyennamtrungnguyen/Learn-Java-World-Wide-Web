
package vn.fit.nguyennamtrungnguyen.repository.impl;

import vn.fit.nguyennamtrungnguyen.model.TinTuc;
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
public class TinTucRepositoryImpl implements vn.fit.nguyennamtrungnguyen.repository.TinTucRepository {

    private TinTuc mapRow(ResultSet rs) throws SQLException {
        return new TinTuc(
                rs.getInt("MATT"),
                rs.getString("TIEUDE"),
                rs.getString("NOIDUNGTT"),
                rs.getString("LIENKET"),
                rs.getInt("MADM")
        );
    }

    // Lấy tất cả tin tức
    @Override
    public List<TinTuc> findAll() throws SQLException {
        List<TinTuc> tinTucList = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tinTucList.add(mapRow(rs));
            }
        }

        return tinTucList;
    }

    // Tìm tin tức theo mã
    @Override
    public TinTuc findById(int maTT) throws SQLException {
        String sql = "SELECT * FROM TINTUC WHERE MATT = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTT);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }

        return null;
    }

    // Thêm tin tức
    @Override
    public boolean save(TinTuc tinTuc) throws SQLException {
        String sql = """
                INSERT INTO TINTUC
                    (TIEUDE, NOIDUNGTT, LIENKET, MADM)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tinTuc.getTIEUDE());
            ps.setString(2, tinTuc.getNOIDUNGTT());
            ps.setString(3, tinTuc.getLIENKET());
            ps.setInt(4, tinTuc.getMADM());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tinTuc.setMATT(rs.getInt(1));
                }
            }

            return true;
        }
    }

    // Cập nhật tin tức
    @Override
    public boolean update(TinTuc tinTuc) throws SQLException {
        String sql = """
                UPDATE TINTUC
                SET TIEUDE = ?,
                    NOIDUNGTT = ?,
                    LIENKET = ?,
                    MADM = ?
                WHERE MATT = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tinTuc.getTIEUDE());
            ps.setString(2, tinTuc.getNOIDUNGTT());
            ps.setString(3, tinTuc.getLIENKET());
            ps.setInt(4, tinTuc.getMADM());
            ps.setInt(5, tinTuc.getMATT());

            return ps.executeUpdate() > 0;
        }
    }

    // Xóa tin tức theo mã
    @Override
    public boolean delete(int maTT) throws SQLException {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTT);

            return ps.executeUpdate() > 0;
        }
    }
}
