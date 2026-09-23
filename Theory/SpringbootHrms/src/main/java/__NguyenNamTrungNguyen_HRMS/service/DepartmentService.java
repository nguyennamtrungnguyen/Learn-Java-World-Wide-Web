package __NguyenNamTrungNguyen_HRMS.service;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import __NguyenNamTrungNguyen_HRMS.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service xử lý logic nghiệp vụ cho Phòng Ban
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    /**
     * Lấy tất cả phòng ban
     */
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    /**
     * Tìm phòng ban theo ID
     */
    @Transactional(readOnly = true)
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    /**
     * Lưu phòng ban (thêm mới hoặc cập nhật)
     */
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Xóa phòng ban theo ID
     */
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    /**
     * Kiểm tra tên phòng ban đã tồn tại chưa
     */
    @Transactional(readOnly = true)
    public boolean existsByName(String deptName) {
        return departmentRepository.findByDeptName(deptName).isPresent();
    }

    /**
     * Đếm tổng số phòng ban
     */
    @Transactional(readOnly = true)
    public long count() {
        return departmentRepository.count();
    }
}
