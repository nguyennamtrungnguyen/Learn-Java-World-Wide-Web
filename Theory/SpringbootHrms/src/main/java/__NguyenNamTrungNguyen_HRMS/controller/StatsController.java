package __NguyenNamTrungNguyen_HRMS.controller;

import __NguyenNamTrungNguyen_HRMS.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller xử lý các trang thống kê
 */
@Controller
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatisticsService statisticsService;

    /**
     * Thống kê nhân viên theo nhóm độ tuổi
     */
    @GetMapping("/age")
    public String ageStatistics(Model model) {
        model.addAttribute("pageTitle",       "Thống Kê Theo Độ Tuổi");
        model.addAttribute("ageGroups",       statisticsService.getAgeGroupStatistics());
        model.addAttribute("deptStats",       statisticsService.getDeptStatistics());
        model.addAttribute("employeesWithAge",statisticsService.getEmployeesWithAge());
        return "stats/age";
    }
}
