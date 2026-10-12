package vn.fit.nguyennamtrungnguyen.dto;

import java.math.BigDecimal;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DepartmentStatisticsDTO {
    private int departmentId;
    private String departmentName;
    private long employeeCount;
    private BigDecimal totalSalary;
    private BigDecimal averageSalary;

    public DepartmentStatisticsDTO() {}

    public DepartmentStatisticsDTO(int departmentId, String departmentName, long employeeCount,
                                BigDecimal totalSalary, BigDecimal averageSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
        this.totalSalary = totalSalary;
        this.averageSalary = averageSalary;
    }

    // getter + setter cho cả 5 trường (bắt buộc, để JSON-B xuất ra đúng tên)
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public long getEmployeeCount() { return employeeCount; }
    public void setEmployeeCount(long employeeCount) { this.employeeCount = employeeCount; }
    public BigDecimal getTotalSalary() { return totalSalary; }
    public void setTotalSalary(BigDecimal totalSalary) { this.totalSalary = totalSalary; }
    public BigDecimal getAverageSalary() { return averageSalary; }
    public void setAverageSalary(BigDecimal averageSalary) { this.averageSalary = averageSalary; }
}