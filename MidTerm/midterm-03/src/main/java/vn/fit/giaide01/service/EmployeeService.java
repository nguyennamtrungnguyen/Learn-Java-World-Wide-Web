package vn.fit.giaide01.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import vn.fit.giaide01.model.Employee;
import vn.fit.giaide01.repository.EmployeeRepository;
import vn.fit.giaide01.repository.impl.EmployeeRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;
    public List<Employee> findAll() throws SQLException {
        return employeeRepository.findAll();
    }
    public Employee findById(int id) throws SQLException{
        return employeeRepository.findById(id);
    }
    public boolean save(Employee employee) throws SQLException{
        return employeeRepository.save(employee);
    }
}
