package vn.fit.giaide01.repository;

import vn.fit.giaide01.model.Employee;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll() throws SQLException;

    Employee findById(int id) throws SQLException;

    boolean save(Employee employee) throws SQLException;

    boolean findByDepartmentAndSalaryRange(int departmentId, BigDecimal minSalary, BigDecimal maxSalary);
}
