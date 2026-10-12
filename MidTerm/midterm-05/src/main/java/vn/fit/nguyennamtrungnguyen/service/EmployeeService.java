package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.repository.DepartmentRepository;
import vn.fit.nguyennamtrungnguyen.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final DepartmentRepository departmentRepository = new DepartmentRepository();

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    /** @throws IllegalArgumentException khi du lieu khong hop le (-> HTTP 400) */
    public Employee create(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("Request body khong duoc rong");
        }
        if (e.getName() == null || e.getName().isBlank()) {
            throw new IllegalArgumentException("name khong duoc de trong");
        }
        if (e.getSalary() == null || e.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("salary phai >= 0");
        }
        if (!departmentRepository.existsById(e.getDepartmentId())) {
            throw new IllegalArgumentException("departmentId " + e.getDepartmentId() + " khong ton tai");
        }
        e.setName(e.getName().trim());
        return employeeRepository.save(e);
    }
}
