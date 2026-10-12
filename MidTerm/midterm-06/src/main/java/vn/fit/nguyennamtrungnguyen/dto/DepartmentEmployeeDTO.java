package vn.fit.nguyennamtrungnguyen.dto;

import vn.fit.nguyennamtrungnguyen.model.Department;
import vn.fit.nguyennamtrungnguyen.model.Employee;

import java.util.List;

public class DepartmentEmployeeDTO {
    private Department department;
    private List<Employee> employees;

    public DepartmentEmployeeDTO(Department department, List<Employee> employees) {
        this.department = department;
        this.employees = employees;
    }

    public DepartmentEmployeeDTO() {
    }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}