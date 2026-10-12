package vn.fit.nguyennamtrungnguyen.model;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name;
    private BigDecimal salary;
    private int departmentId;

    public Employee() {
    }

    public Employee(int id, String name, BigDecimal salary, int departmentId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
}
