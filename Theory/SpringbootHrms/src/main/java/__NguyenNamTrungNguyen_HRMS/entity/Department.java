package __NguyenNamTrungNguyen_HRMS.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity đại diện cho Phòng Ban (Department)
 * Quan hệ: 1 Department → nhiều Employee (OneToMany)
 */
@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name", nullable = false, unique = true, length = 100)
    private String deptName;

    /**
     * Danh sách nhân viên thuộc phòng ban này
     * mappedBy = "dept" → khóa ngoại được quản lý bởi field dept trong Employee
     * cascade = REMOVE → khi xóa dept thì cập nhật employees (không xóa employees)
     */
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    @Override
    public String toString() {
        return "Department{deptId=" + deptId + ", deptName='" + deptName + "'}";
    }
}
