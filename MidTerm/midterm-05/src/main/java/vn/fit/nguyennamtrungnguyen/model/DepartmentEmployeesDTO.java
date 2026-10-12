package vn.fit.nguyennamtrungnguyen.model;

import java.util.ArrayList;
import java.util.List;

/** Noi dung 4 - Response chua ca Department va danh sach Employee. */
public class DepartmentEmployeesDTO {
    private Department department;
    private List<Employee> employees = new ArrayList<>();

    public DepartmentEmployeesDTO() {
    }

    public DepartmentEmployeesDTO(Department department, List<Employee> employees) {
        this.department = department;
        this.employees = employees;
    }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
