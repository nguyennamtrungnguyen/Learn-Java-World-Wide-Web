package __NguyenNamTrungNguyen_HRMS.controller;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import __NguyenNamTrungNguyen_HRMS.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controller xử lý CRUD cho Phòng Ban
 */
@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Danh sách tất cả phòng ban
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle", "Quản Lý Phòng Ban");
        return "departments/list";
    }

    /**
     * Form thêm phòng ban mới
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Thêm Phòng Ban");
        model.addAttribute("isEdit", false);
        return "departments/form";
    }

    /**
     * Form sửa phòng ban
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<Department> dept = departmentService.findById(id);
        if (dept.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy phòng ban với ID: " + id);
            return "redirect:/departments";
        }
        model.addAttribute("department", dept.get());
        model.addAttribute("pageTitle", "Sửa Phòng Ban");
        model.addAttribute("isEdit", true);
        return "departments/form";
    }

    /**
     * Lưu phòng ban (thêm mới hoặc cập nhật)
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Department department, RedirectAttributes ra) {
        try {
            departmentService.save(department);
            String action = (department.getDeptId() == null || department.getDeptId() == 0) ? "thêm" : "cập nhật";
            ra.addFlashAttribute("successMessage", "Đã " + action + " phòng ban thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/departments";
    }

    /**
     * Xóa phòng ban theo ID
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            departmentService.deleteById(id);
            ra.addFlashAttribute("successMessage", "Đã xóa phòng ban thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Không thể xóa phòng ban (có thể đang có nhân viên).");
        }
        return "redirect:/departments";
    }
}
