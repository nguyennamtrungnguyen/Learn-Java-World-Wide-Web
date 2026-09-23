package __NguyenNamTrungNguyen_HRMS.service;

import __NguyenNamTrungNguyen_HRMS.entity.Employee;
import __NguyenNamTrungNguyen_HRMS.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

/**
 * Service thống kê nhân viên theo nhiều tiêu chí
 * Bao gồm: thống kê theo nhóm tuổi, theo phòng ban
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final EmployeeRepository employeeRepository;

    /**
     * DTO lưu thông tin thống kê theo nhóm tuổi
     */
    public record AgeGroupStats(
            String groupLabel,      // Tên nhóm tuổi
            int minAge,             // Tuổi tối thiểu của nhóm
            int maxAge,             // Tuổi tối đa của nhóm (-1 = không giới hạn)
            long count,             // Số nhân viên trong nhóm
            double percentage       // Tỷ lệ phần trăm
    ) {}

    /**
     * DTO lưu thông tin thống kê theo phòng ban
     */
    public record DeptStats(
            String deptName,
            long count,
            double percentage
    ) {}

    /**
     * Tính tuổi của nhân viên tính đến hôm nay
     */
    public int calculateAge(Date dob) {
        if (dob == null) return 0;
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Thống kê nhân viên theo nhóm độ tuổi:
     * - Nhóm 1: Dưới 25 tuổi
     * - Nhóm 2: 25 - 35 tuổi
     * - Nhóm 3: 36 - 45 tuổi
     * - Nhóm 4: Trên 45 tuổi
     *
     * @return Danh sách AgeGroupStats cho từng nhóm
     */
    public List<AgeGroupStats> getAgeGroupStatistics() {
        List<Employee> allEmployees = employeeRepository.findAll();
        long total = allEmployees.size();

        if (total == 0) {
            return Collections.emptyList();
        }

        long under25 = 0, age25to35 = 0, age36to45 = 0, over45 = 0;

        for (Employee emp : allEmployees) {
            int age = calculateAge(emp.getDob());
            if (age < 25) {
                under25++;
            } else if (age <= 35) {
                age25to35++;
            } else if (age <= 45) {
                age36to45++;
            } else {
                over45++;
            }
        }

        List<AgeGroupStats> result = new ArrayList<>();
        result.add(new AgeGroupStats("Dưới 25 tuổi",   0,  24, under25,   round(under25,  total)));
        result.add(new AgeGroupStats("25 - 35 tuổi",  25,  35, age25to35, round(age25to35, total)));
        result.add(new AgeGroupStats("36 - 45 tuổi",  36,  45, age36to45, round(age36to45, total)));
        result.add(new AgeGroupStats("Trên 45 tuổi",  46, 999, over45,    round(over45,    total)));

        return result;
    }

    /**
     * Thống kê nhân viên theo phòng ban (để hiển thị trên trang chủ)
     */
    public List<DeptStats> getDeptStatistics() {
        List<Employee> allEmployees = employeeRepository.findAll();
        long total = allEmployees.size();

        if (total == 0) return Collections.emptyList();

        Map<String, Long> deptCount = new LinkedHashMap<>();
        for (Employee emp : allEmployees) {
            String deptName = emp.getDept() != null ? emp.getDept().getDeptName() : "Chưa xác định";
            deptCount.merge(deptName, 1L, Long::sum);
        }

        List<DeptStats> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : deptCount.entrySet()) {
            result.add(new DeptStats(entry.getKey(), entry.getValue(), round(entry.getValue(), total)));
        }

        // Sắp xếp giảm dần theo số lượng
        result.sort((a, b) -> Long.compare(b.count(), a.count()));
        return result;
    }

    /**
     * Lấy danh sách nhân viên kèm tuổi để hiển thị trong bảng thống kê
     */
    public List<Map<String, Object>> getEmployeesWithAge() {
        List<Employee> employees = employeeRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Employee emp : employees) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("empId",   emp.getEmpId());
            row.put("empName", emp.getEmpName());
            row.put("dob",     emp.getDob());
            row.put("age",     calculateAge(emp.getDob()));
            row.put("dept",    emp.getDept() != null ? emp.getDept().getDeptName() : "N/A");
            result.add(row);
        }

        // Sắp xếp theo tuổi tăng dần
        result.sort(Comparator.comparingInt(m -> (int) m.get("age")));
        return result;
    }

    /**
     * Tính tỷ lệ phần trăm (làm tròn 2 chữ số)
     */
    private double round(long part, long total) {
        return total == 0 ? 0.0 : Math.round((double) part / total * 10000.0) / 100.0;
    }
}
