package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.DepartmentEmployeesDTO;
import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.www_java_midterm.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private Employee map(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("salary"),
                rs.getInt("department_id"));
    }

    public List<Employee> findAll() {
        String sql = "SELECT id, name, salary, department_id FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi truy van employees: " + e.getMessage(), e);
        }
        return list;
    }

    public Optional<Employee> findById(int id) {
        String sql = "SELECT id, name, salary, department_id FROM employees WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi truy van employee: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    /** Them nhan vien moi, tra ve Employee kem id vua sinh. */
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employees (name, department_id, salary) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setBigDecimal(3, employee.getSalary());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    employee.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi them employee: " + e.getMessage(), e);
        }
        return employee;
    }

    /**
     * Noi dung 2c: truy van lien ket departments - employees, loc theo ma phong ban
     * va khoang luong. Tra ve null neu phong ban khong ton tai; neu ton tai nhung
     * khong co nhan vien phu hop thi danh sach employees rong.
     */
    public DepartmentEmployeesDTO findByDepartmentAndSalaryRange(int departmentId,
                                                                 BigDecimal minSalary,
                                                                 BigDecimal maxSalary) {
        String sql = """
                SELECT d.id AS d_id, d.name AS d_name,
                       e.id, e.name, e.salary, e.department_id
                FROM departments d
                LEFT JOIN employees e
                       ON e.department_id = d.id
                      AND e.salary BETWEEN ? AND ?
                WHERE d.id = ?
                ORDER BY e.id
                """;
        DepartmentEmployeesDTO result = null;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setBigDecimal(1, minSalary);
            ps.setBigDecimal(2, maxSalary);
            ps.setInt(3, departmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (result == null) {
                        result = new DepartmentEmployeesDTO(
                                new Department(rs.getInt("d_id"), rs.getString("d_name")),
                                new ArrayList<>());
                    }
                    rs.getInt("id");
                    if (!rs.wasNull()) {          // LEFT JOIN: dong khong co nhan vien
                        result.getEmployees().add(map(rs));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loi truy van employees theo department: " + e.getMessage(), e);
        }
        return result;
    }
}
