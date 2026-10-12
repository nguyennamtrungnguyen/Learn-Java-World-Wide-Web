package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.DepartmentStatistics;
import vn.fit.www_java_midterm.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository {

    public List<Department> findAll() {
        String sql = "SELECT id, name FROM departments ORDER BY id";
        List<Department> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Department(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi truy van departments: " + e.getMessage(), e);
        }
        return list;
    }

    public Optional<Department> findById(int id) {
        String sql = "SELECT id, name FROM departments WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Department(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi truy van department: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }

    /** Noi dung 5: LEFT JOIN + GROUP BY + COUNT/SUM/AVG. */
    public List<DepartmentStatistics> getStatistics() {
        String sql = """
                SELECT d.id AS departmentId,
                       d.name AS departmentName,
                       COUNT(e.id) AS employeeCount,
                       COALESCE(SUM(e.salary), 0) AS totalSalary,
                       COALESCE(ROUND(AVG(e.salary), 2), 0) AS averageSalary
                FROM departments d
                LEFT JOIN employees e ON e.department_id = d.id
                GROUP BY d.id, d.name
                ORDER BY d.id
                """;
        List<DepartmentStatistics> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DepartmentStatistics(
                        rs.getInt("departmentId"),
                        rs.getString("departmentName"),
                        rs.getLong("employeeCount"),
                        rs.getBigDecimal("totalSalary"),
                        rs.getBigDecimal("averageSalary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi thong ke departments: " + e.getMessage(), e);
        }
        return list;
    }
}
