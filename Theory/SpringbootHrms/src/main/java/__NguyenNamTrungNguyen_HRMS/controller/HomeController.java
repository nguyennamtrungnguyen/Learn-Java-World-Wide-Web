package __NguyenNamTrungNguyen_HRMS.controller;

import __NguyenNamTrungNguyen_HRMS.service.DepartmentService;
import __NguyenNamTrungNguyen_HRMS.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller cho trang chủ (Dashboard)
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalEmployees",   employeeService.count());
        model.addAttribute("totalDepartments", departmentService.count());
        return "index";
    }
}
