package vn.fit.nguyennamtrungnguyen.service;

import vn.fit.nguyennamtrungnguyen.model.Employee;
import vn.fit.nguyennamtrungnguyen.repository.EmployeeRepository;
import java.sql.*;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    //    để lấy toàn bộ nhân viên.
    public List<Employee> findAll() {
       return employeeRepository.findAll();
    }

    //    để lấy một nhân viên theo mã nhân viên.
    public Employee findById(int id){
        return employeeRepository.findById(id);
    }
    public List<Employee> findByDateHire(Date date) {
        return employeeRepository.findByDateHire(date);
    }

    //    để thêm nhân viên mới vào bảng employees.
    public boolean save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean update( int id, Employee employee){
        return employeeRepository.update(id, employee);
    }

    public boolean delete(int id){
        return employeeRepository.delete(id);
    }

}
