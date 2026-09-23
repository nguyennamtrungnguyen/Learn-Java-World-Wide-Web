package __NguyenNamTrungNguyen_HRMS.service;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import __NguyenNamTrungNguyen_HRMS.entity.Employee;
import __NguyenNamTrungNguyen_HRMS.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service xử lý logic nghiệp vụ cho Nhân Viên
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Lấy tất cả nhân viên
     */
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Tìm nhân viên theo ID
     */
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Lưu nhân viên (thêm mới hoặc cập nhật)
     */
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Xóa nhân viên theo ID
     */
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * Lấy nhân viên theo phòng ban
     */
    @Transactional(readOnly = true)
    public List<Employee> findByDept(Department dept) {
        return employeeRepository.findByDept(dept);
    }

    /**
     * Lấy nhân viên theo deptId
     */
    @Transactional(readOnly = true)
    public List<Employee> findByDeptId(Long deptId) {
        return employeeRepository.findByDeptDeptId(deptId);
    }

    /**
     * Tìm kiếm nhân viên theo tên
     */
    @Transactional(readOnly = true)
    public List<Employee> searchByName(String keyword) {
        return employeeRepository.findByEmpNameContainingIgnoreCase(keyword);
    }

    /**
     * Đếm tổng số nhân viên
     */
    @Transactional(readOnly = true)
    public long count() {
        return employeeRepository.count();
    }

    /**
     * Đếm nhân viên theo phòng ban
     */
    @Transactional(readOnly = true)
    public long countByDeptId(Long deptId) {
        return employeeRepository.countByDeptId(deptId);
    }
}
