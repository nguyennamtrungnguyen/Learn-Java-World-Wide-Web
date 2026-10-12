package vn.fit.nguyennamtrungnguyen.repository;

import vn.fit.nguyennamtrungnguyen.dto.DepartmentEmployeeDTO;
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
                        rs.getBigDecimal("salary")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //    để lấy một nhân viên theo mã nhân viên.
    public Department findById(int id){
        return null;
    }

    //    để thêm nhân viên mới vào bảng employees.
    public boolean save(Employee employee){
        return true;
    }

//    để truy vấn có liên kết giữa hai bảng departments và employees, lọc theo mã phòng ban và khoảng lương, đồng thời trả về thông tin Department kèm danh sách Employee.
    public List<DepartmentEmployeeDTO> findByDepartmentAndSalaryRange(int departmentId, BigDecimal minSalary, BigDecimal maxSalary){
        return null;
    }

}
