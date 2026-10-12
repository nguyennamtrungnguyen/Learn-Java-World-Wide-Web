package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class EmployeeRepository {

    //    để lấy toàn bộ nhân viên.
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = """
                select * from employees
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("department_id"),
                        rs.getBigDecimal("salary"),
                        rs.getDate("hire_date")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //    để lấy một nhân viên theo mã nhân viên.
    public Employee findById(int id) {
        String sql = """
                select *
                from employees 
                where id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("department_id"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("hire_date")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Employee> findByDateHire(Date date) {
        List<Employee> list = new ArrayList<>();
        String sql = """
                select *
                from employees 
                where hire_date = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setDate(1,date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("department_id"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("hire_date")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //    để thêm nhân viên mới vào bảng employees.
    public boolean save(Employee employee) {
        String sql = """
                insert into employees(name, department_id, salary, hire_date) 
                values(?,?,?,?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setBigDecimal(3, employee.getSalary());
            ps.setDate(4, employee.getHireDate());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(int id, Employee employee) {
        String sql = """
                update employees
                set name = ?,
                    department_id = ?,
                    salary = ?,
                    hire_date = ?
                    where id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setBigDecimal(3, employee.getSalary());
            ps.setDate(4, employee.getHireDate());
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String sql = """
                delete from employees
                where id = ?
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
