package __NguyenNamTrungNguyen_HRMS.repository;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository cho Department
 * Kế thừa JpaRepository → tự động có: findAll, findById, save, deleteById
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * Tìm phòng ban theo tên (dùng để kiểm tra trùng tên)
     */
    Optional<Department> findByDeptName(String deptName);
}
