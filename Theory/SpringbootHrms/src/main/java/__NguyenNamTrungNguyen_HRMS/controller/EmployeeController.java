package __NguyenNamTrungNguyen_HRMS.controller;

import __NguyenNamTrungNguyen_HRMS.entity.Department;
import __NguyenNamTrungNguyen_HRMS.entity.Employee;
import __NguyenNamTrungNguyen_HRMS.service.DepartmentService;
import __NguyenNamTrungNguyen_HRMS.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controller xử lý CRUD cho Nhân Viên
 */
@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    /**
     * Danh sách nhân viên (có thể lọc theo phòng ban hoặc tìm kiếm theo tên)
     */
    @GetMapping
    public String list(@RequestParam(required = false) Long deptId,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        List<Employee> employees;

        if (keyword != null && !keyword.isBlank()) {
            employees = employeeService.searchByName(keyword.trim());
            model.addAttribute("keyword", keyword);
        } else if (deptId != null) {
            employees = employeeService.findByDeptId(deptId);
            model.addAttribute("selectedDeptId", deptId);
        } else {
            employees = employeeService.findAll();
        }

        model.addAttribute("employees",   employees);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle",   "Quản Lý Nhân Viên");
        return "employees/list";
    }

    /**
     * Form thêm nhân viên mới
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee",    new Employee());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle",   "Thêm Nhân Viên");
        model.addAttribute("isEdit",      false);
        return "employees/form";
    }

    /**
     * Form sửa nhân viên
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<Employee> emp = employeeService.findById(id);
        if (emp.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy nhân viên với ID: " + id);
            return "redirect:/employees";
        }
        model.addAttribute("employee",    emp.get());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("pageTitle",   "Sửa Nhân Viên");
        model.addAttribute("isEdit",      true);
        return "employees/form";
    }

    /**
     * Lưu nhân viên (thêm mới hoặc cập nhật)
     * dept được bind qua deptId từ form select
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee,
                       @RequestParam Long deptId,
                       RedirectAttributes ra) {
        try {
            Optional<Department> dept = departmentService.findById(deptId);
            if (dept.isEmpty()) {
                ra.addFlashAttribute("errorMessage", "Phòng ban không hợp lệ!");
                return "redirect:/employees/add";
            }
            employee.setDept(dept.get());
            employeeService.save(employee);
            String action = (employee.getEmpId() == null || employee.getEmpId() == 0) ? "thêm" : "cập nhật";
            ra.addFlashAttribute("successMessage", "Đã " + action + " nhân viên thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi khi lưu nhân viên: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    /**
     * Xóa nhân viên theo ID
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            employeeService.deleteById(id);
            ra.addFlashAttribute("successMessage", "Đã xóa nhân viên thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi khi xóa nhân viên: " + e.getMessage());
        }
        return "redirect:/employees";
    }
}
