package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.DepartmentEmployeesDTO;
import vn.fit.nguyennamtrungnguyen.model.DepartmentStatistics;
import vn.fit.nguyennamtrungnguyen.repository.DepartmentRepository;
import vn.fit.nguyennamtrungnguyen.repository.EmployeeRepository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DepartmentService {

    private static final BigDecimal DEFAULT_MAX = new BigDecimal("9999999999.99");

    private final DepartmentRepository departmentRepository = new DepartmentRepository();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    /**
     * Noi dung 4.
     * @return Optional.empty() neu departmentId khong ton tai (-> HTTP 404)
     * @throws IllegalArgumentException neu minSalary > maxSalary (-> HTTP 400)
     */
    public Optional<DepartmentEmployeesDTO> findEmployeesByDepartment(int departmentId,
                                                                      BigDecimal minSalary,
                                                                      BigDecimal maxSalary) {
        BigDecimal min = (minSalary == null) ? BigDecimal.ZERO : minSalary;
        BigDecimal max = (maxSalary == null) ? DEFAULT_MAX : maxSalary;
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("minSalary khong duoc lon hon maxSalary");
        }
        if (!departmentRepository.existsById(departmentId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                employeeRepository.findByDepartmentAndSalaryRange(departmentId, min, max));
    }

    /** Noi dung 5. */
    public List<DepartmentStatistics> getStatistics() {
        return departmentRepository.getStatistics();
    }
}
