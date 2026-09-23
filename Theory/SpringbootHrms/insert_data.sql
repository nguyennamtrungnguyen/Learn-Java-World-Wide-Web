-- ============================================================
-- HRMS DATA SCRIPT - 5 Phòng Ban + 100 Nhân Viên
-- Chạy script này SAU KHI ứng dụng Spring Boot đã khởi động
-- (JPA sẽ tự tạo bảng department và employee trước)
--
-- Cách chạy:
--   mysql -u root -p hrms_db < insert_data.sql
--   hoặc chạy trực tiếp trong HeidiSQL / DBeaver / MySQL Workbench
-- ============================================================

USE hrms_db;

-- ============================================================
-- 1. XÓA DỮ LIỆU CŨ (nếu có) để tránh trùng lặp
-- ============================================================
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE employee;
TRUNCATE TABLE department;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================
-- 2. THÊM 5 PHÒNG BAN
-- ============================================================
INSERT INTO department (dept_id, dept_name) VALUES
(1, 'Công Nghệ Thông Tin'),
(2, 'Nhân Sự'),
(3, 'Tài Chính - Kế Toán'),
(4, 'Marketing'),
(5, 'Vận Hành - Hậu Cần');

-- ============================================================
-- 3. THÊM 100 NHÂN VIÊN (phân bổ đều: mỗi phòng 20 người)
--    Ngày sinh đa dạng: 1970 - 2005 (tuổi 21 - 56)
--    Nhóm tuổi phân bổ:
--      < 25 tuổi    (sinh 2002-2005): ~20 người
--      25-35 tuổi   (sinh 1991-2001): ~30 người
--      36-45 tuổi   (sinh 1981-1990): ~30 người
--      > 45 tuổi    (sinh 1970-1980): ~20 người
-- ============================================================
INSERT INTO employee (emp_name, dob, dept_id) VALUES

-- ========== PHÒNG CNTT (dept_id=1) - 20 nhân viên ==========
('Nguyễn Văn An',        '1985-03-15', 1),
('Trần Thị Bình',        '1990-07-22', 1),
('Lê Minh Cường',        '2003-11-08', 1),
('Phạm Thị Dung',        '1995-04-30', 1),
('Hoàng Văn Em',         '1978-09-12', 1),
('Vũ Thị Phương',        '2001-06-25', 1),
('Đặng Minh Giang',      '1988-01-17', 1),
('Bùi Thị Hà',           '1972-12-03', 1),
('Ngô Văn Hùng',         '1997-08-19', 1),
('Đinh Thị Lan',         '2004-02-14', 1),
('Lý Văn Khánh',         '1983-05-28', 1),
('Phan Thị Linh',        '1993-10-07', 1),
('Cao Văn Minh',         '2002-03-21', 1),
('Đỗ Thị Ngọc',          '1976-07-09', 1),
('Hồ Văn Nam',           '1999-11-30', 1),
('Mai Thị Oanh',         '1987-04-16', 1),
('Trịnh Văn Phát',       '2005-09-04', 1),
('Lưu Thị Quỳnh',        '1991-01-25', 1),
('Dương Văn Sơn',        '1980-06-11', 1),
('Chu Thị Trang',        '1996-12-18', 1),

-- ========== PHÒNG NHÂN SỰ (dept_id=2) - 20 nhân viên ==========
('Nguyễn Thị Uyên',      '1984-02-28', 2),
('Trần Văn Vinh',        '1992-08-14', 2),
('Lê Thị Xuân',          '2003-05-06', 2),
('Phạm Văn Yên',         '1977-11-20', 2),
('Hoàng Thị Zara',       '2000-03-31', 2),
('Vũ Văn Anh',           '1989-07-15', 2),
('Đặng Thị Bảo',         '1973-01-08', 2),
('Bùi Văn Chiến',        '1998-09-22', 2),
('Ngô Thị Diệu',         '2004-04-17', 2),
('Đinh Văn Đức',         '1982-12-05', 2),
('Lý Thị Erin',          '1994-06-29', 2),
('Phan Văn Hoà',         '2001-10-13', 2),
('Cao Thị Iris',         '1979-03-07', 2),
('Đỗ Văn Kỳ',            '1986-08-24', 2),
('Hồ Thị Lụa',           '1995-02-19', 2),
('Mai Văn Mạnh',         '2002-07-31', 2),
('Trịnh Thị Nhi',        '1971-05-16', 2),
('Lưu Văn Oai',          '1990-11-03', 2),
('Dương Thị Phúc',       '2005-01-27', 2),
('Chu Văn Quang',        '1983-04-12', 2),

-- ========== PHÒNG TÀI CHÍNH (dept_id=3) - 20 nhân viên ==========
('Nguyễn Văn Roảnh',     '1975-06-08', 3),
('Trần Thị Sen',         '1993-10-24', 3),
('Lê Văn Thắng',         '2002-03-16', 3),
('Phạm Thị Uyên',        '1987-08-11', 3),
('Hoàng Văn Viễn',       '1999-01-29', 3),
('Vũ Thị Huyền',         '1981-07-04', 3),
('Đặng Văn Xoan',        '2004-11-18', 3),
('Bùi Thị Yến',          '1970-04-22', 3),
('Ngô Văn Zũng',         '1996-09-07', 3),
('Đinh Thị Ái',          '2003-02-13', 3),
('Lý Văn Bảy',           '1985-06-30', 3),
('Phan Thị Cẩm',         '1991-12-17', 3),
('Cao Văn Dần',          '2001-05-21', 3),
('Đỗ Thị Ếch',           '1978-10-09', 3),
('Hồ Văn Phong',         '1997-03-14', 3),
('Mai Thị Giang',        '1984-07-26', 3),
('Trịnh Văn Hậu',        '2005-08-02', 3),
('Lưu Thị Inh',          '1992-01-11', 3),
('Dương Văn Lâm',        '1974-05-19', 3),
('Chu Thị Kim',          '1998-09-28', 3),

-- ========== PHÒNG MARKETING (dept_id=4) - 20 nhân viên ==========
('Nguyễn Thị Loan',      '1986-04-05', 4),
('Trần Văn Mẫn',         '1994-08-21', 4),
('Lê Thị Nha',           '2003-01-10', 4),
('Phạm Văn Ổn',          '1976-06-27', 4),
('Hoàng Thị Phượng',     '2000-11-14', 4),
('Vũ Văn Rồng',          '1989-03-08', 4),
('Đặng Thị Sương',       '1972-08-31', 4),
('Bùi Văn Tân',          '1997-12-19', 4),
('Ngô Thị Thắm',         '2004-05-23', 4),
('Đinh Văn Uy',          '1981-10-02', 4),
('Lý Thị Vân',           '1995-02-16', 4),
('Phan Văn Xuân',        '2002-07-07', 4),
('Cao Thị Yến',          '1980-01-25', 4),
('Đỗ Văn Zùng',          '1988-06-13', 4),
('Hồ Thị Ánh',           '1993-10-30', 4),
('Mai Văn Bình',         '2001-04-08', 4),
('Trịnh Thị Công',       '1970-09-15', 4),
('Lưu Văn Duy',          '1999-02-22', 4),
('Dương Thị Ế',          '1983-07-17', 4),
('Chu Văn Phú',          '2005-03-06', 4),

-- ========== PHÒNG VẬN HÀNH (dept_id=5) - 20 nhân viên ==========
('Nguyễn Văn Gắng',      '1977-05-12', 5),
('Trần Thị Hoan',        '1991-09-28', 5),
('Lê Văn Iên',           '2003-04-03', 5),
('Phạm Thị Khánh',       '1985-11-16', 5),
('Hoàng Văn Lực',        '1998-07-04', 5),
('Vũ Thị Minh',          '1979-02-20', 5),
('Đặng Văn Ngọc',        '2004-08-09', 5),
('Bùi Thị Oanh',         '1973-01-15', 5),
('Ngô Văn Phúc',         '1996-06-22', 5),
('Đinh Thị Quyên',       '2002-12-31', 5),
('Lý Văn Rệu',           '1988-04-18', 5),
('Phan Thị Sắc',         '1994-10-05', 5),
('Cao Văn Tấn',          '2000-03-27', 5),
('Đỗ Thị Uyên',          '1982-08-14', 5),
('Hồ Văn Vinh',          '1997-01-21', 5),
('Mai Thị Xuân',         '1987-05-30', 5),
('Trịnh Văn Yên',        '2005-11-12', 5),
('Lưu Thị Zéo',          '1990-07-08', 5),
('Dương Văn Ân',         '1975-03-24', 5),
('Chu Thị Bông',         '1999-10-17', 5);

-- ============================================================
-- KIỂM TRA KẾT QUẢ
-- ============================================================
SELECT 'Tổng phòng ban:' AS thong_ke, COUNT(*) AS so_luong FROM department
UNION ALL
SELECT 'Tổng nhân viên:', COUNT(*) FROM employee;

SELECT d.dept_name, COUNT(e.emp_id) AS so_nhan_vien
FROM department d
LEFT JOIN employee e ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name
ORDER BY so_nhan_vien DESC;
