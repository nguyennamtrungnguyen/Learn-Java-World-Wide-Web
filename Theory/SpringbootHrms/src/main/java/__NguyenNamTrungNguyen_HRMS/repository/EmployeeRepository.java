package __NguyenNamTrungNguyen_HRMS.repository;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import __NguyenNamTrungNguyen_HRMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository cho Employee
 * Bao gồm các query tùy chỉnh: lọc theo phòng ban, tìm theo ngày sinh
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Tìm nhân viên theo phòng ban
     */
    List<Employee> findByDept(Department dept);

    /**
     * Tìm nhân viên theo deptId (join)
     */
    List<Employee> findByDeptDeptId(Long deptId);

    /**
     * Tìm nhân viên có ngày sinh trong khoảng (dùng cho thống kê độ tuổi)
     */
    List<Employee> findByDobBetween(Date startDate, Date endDate);

    /**
     * Tìm nhân viên có ngày sinh trước một mốc (nhóm tuổi > N)
     */
    List<Employee> findByDobBefore(Date date);

    /**
     * Tìm nhân viên có ngày sinh sau một mốc (nhóm tuổi < N)
     */
    List<Employee> findByDobAfter(Date date);

    /**
     * Tìm nhân viên theo tên (tìm kiếm gợi ý)
     */
    List<Employee> findByEmpNameContainingIgnoreCase(String keyword);

    /**
     * Đếm nhân viên theo phòng ban
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.dept.deptId = :deptId")
    long countByDeptId(Long deptId);
}
