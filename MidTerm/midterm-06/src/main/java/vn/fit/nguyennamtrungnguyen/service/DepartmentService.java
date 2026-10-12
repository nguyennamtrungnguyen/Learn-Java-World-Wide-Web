package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.dto.DepartmentEmployeeDTO;
import vn.fit.nguyennamtrungnguyen.dto.DepartmentStatisticsDTO;
import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.repository.DepartmentRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DepartmentService {
    private final DepartmentRepository departmentRepository = new DepartmentRepository();
    //    để truy vấn có liên kết giữa hai bảng departments và employees, lọc theo mã phòng ban và khoảng lương, đồng thời trả về thông tin Department kèm danh sách Employee.
    public DepartmentEmployeeDTO findEmployeesByDepartment(int departmentId,
                                                            BigDecimal minSalary,
                                                            BigDecimal maxSalary) {
        if (minSalary == null) {
            minSalary = BigDecimal.ZERO;
        }
        if (maxSalary == null) {
            maxSalary = new BigDecimal("9999999999");
        }

        // Kiểm tra phòng ban tồn tại
        Department department = departmentRepository.findById(departmentId);
        if (department == null) {
            return null;   // controller trả 404
        }

        List<Employee> employees = departmentRepository.findByDepartmentAndSalaryRange(departmentId, minSalary, maxSalary);

        return new DepartmentEmployeeDTO(department, employees);
    }

    public List<DepartmentStatisticsDTO> getStatistics() {
        return departmentRepository.getStatistics();
    }

}
