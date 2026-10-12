package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.dto.DepartmentEmployeeDTO;
import vn.fit.nguyennamtrungnguyen.dto.DepartmentStatisticsDTO;
import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DepartmentRepository {

    public Department findById(int id) {
        String sql = """
                select *
                from departments 
                where id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //    để truy vấn có liên kết giữa hai bảng departments và employees,
    //    lọc theo mã phòng ban và khoảng lương,
    //    đồng thời trả về thông tin Department kèm danh sách Employee.
    public List<Employee> findByDepartmentAndSalaryRange(int departmentId,
                                                         BigDecimal minSalary,
                                                         BigDecimal maxSalary) {
        List<Employee> list = new ArrayList<>();
        String sql = """
            select e.id, e.name, e.salary, e.department_id, e.hire_date
            from employees e
            join departments d on e.department_id = d.id
            where e.department_id = ?
              and e.salary between ? and ?
            order by e.id
            """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setBigDecimal(2, minSalary);
            ps.setBigDecimal(3, maxSalary);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("department_id"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("hire_date")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<DepartmentStatisticsDTO> getStatistics() {
        List<DepartmentStatisticsDTO> list = new ArrayList<>();
        String sql = """
            select d.id as departmentId,
                   d.name as departmentName,
                   count(e.id) as employeeCount,
                   coalesce(sum(e.salary), 0) as totalSalary,
                   coalesce(round(avg(e.salary), 2), 0) as averageSalary
            from departments d
            left join employees e on e.department_id = d.id
            group by d.id, d.name
            order by d.id
            """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DepartmentStatisticsDTO(
                        rs.getInt("departmentId"),
                        rs.getString("departmentName"),
                        rs.getLong("employeeCount"),
                        rs.getBigDecimal("totalSalary"),
                        rs.getBigDecimal("averageSalary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
