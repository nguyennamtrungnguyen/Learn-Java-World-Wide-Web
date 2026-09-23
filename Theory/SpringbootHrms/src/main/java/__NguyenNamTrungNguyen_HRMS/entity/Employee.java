package __NguyenNamTrungNguyen_HRMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Entity đại diện cho Nhân Viên (Employee)
 * Quan hệ: nhiều Employee → 1 Department (ManyToOne)
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "emp_name", nullable = false, length = 150)
    private String empName;

    /**
     * Ngày sinh nhân viên (Date of Birth)
     * Dùng để tính tuổi trong service thống kê
     */
    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    /**
     * Phòng ban nhân viên thuộc về
     * ManyToOne: nhiều nhân viên cùng 1 phòng ban
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = false)
    private Department dept;

    @Override
    public String toString() {
        return "Employee{empId=" + empId + ", empName='" + empName + "', dob=" + dob + "}";
    }
}
