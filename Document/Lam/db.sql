-- ============================================================
--  db.sql - Lap trinh WWW (Java) - De giua ky (Ma de 106)
--  Database: CompanyDB (MariaDB)
--  Quan he: departments (1) --< employees (N)
-- ============================================================

DROP DATABASE IF EXISTS CompanyDB;
CREATE DATABASE CompanyDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE CompanyDB;

CREATE TABLE departments (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE employees (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(150)  NOT NULL,
    department_id INT           NOT NULL,
    salary        DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES departments (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 9 records bang departments
INSERT INTO departments (name) VALUES
('Phong Nhan su'),
('Phong Ke toan'),
('Phong Kinh doanh'),
('Phong Marketing'),
('Phong Cong nghe thong tin'),
('Phong Hanh chinh'),
('Phong Ky thuat'),
('Phong Cham soc khach hang'),
('Phong Nghien cuu phat trien');

-- 100 records bang employees (phan bo vao 9 department)
INSERT INTO employees (name, department_id, salary) VALUES
('Duong Thu Ha',              5,  19500000),
('Do Thu Dat',                5,  17700000),
('Bui Quoc Nhung',            6,  13100000),
('Ly Van Mai',                1,  10600000),
('Huynh Ngoc Hoa',            5,  19300000),
('Tran Kim Giang',            5,  19500000),
('Phan Minh Trang',           5,  21200000),
('Le Thu Trang',              8,  10800000),
('Do Duc Son',                2,  26800000),
('Pham Ngoc Khoa',            7,  21700000),
('Bui Duc Dung',              4,  23000000),
('Hoang Duc Trang',           8,   9000000),
('Pham Thanh Binh',           5,  28000000),
('Ngo Quoc Hanh',             4,  30000000),
('Pham Quoc Nam',             3,  11100000),
('Hoang Bao Huong',           5,  43800000),
('Ho Quoc Dat',               5,  32600000),
('Ly Hoang Linh',             3,   9600000),
('Do Gia Khoa',               4,  20600000),
('Dang Bao Khoa',             8,  10400000),
('Bui Quoc Nam',              8,  13400000),
('Ho Thanh Thao',             2,  16100000),
('Pham Ngoc Trung',           3,   9100000),
('Bui Van Khoa',              3,  17000000),
('Huynh Thu Hoa',             6,  10200000),
('Huynh Duc Dat',             9,  29200000),
('Hoang Gia Cuong',           7,  16300000),
('Phan Minh Long',            2,  14800000),
('Phan Huu Hai',              3,  30400000),
('Hoang Ngoc Nam',            2,  13300000),
('Phan Hoang Nhi',            5,  15300000),
('Ly Van Tien',               1,  22400000),
('Do Duc An',                 4,  20600000),
('Bui Thi Huong',             1,  18500000),
('Ly Gia Binh',               6,  12200000),
('Dang Thanh An',             7,  37800000),
('Vu Duc Giang',              9,  44400000),
('Phan Anh Hieu',             3,  10100000),
('Ngo Anh Linh',              7,  27600000),
('Duong Kim Tien',            2,  17900000),
('Ngo Thu Nga',               1,  14700000),
('Do Hoang Trang',            2,  23700000),
('Tran Huu Nam',              4,  23700000),
('Pham Anh Dat',              3,  33500000),
('Dang Anh Linh',             5,  40700000),
('Tran Thanh Quan',           8,   7800000),
('Phan Anh Vy',               1,  18300000),
('Duong Minh Trung',          5,  44800000),
('Duong Ngoc Giang',          7,  13700000),
('Huynh Quoc Quang',          1,  21500000),
('Huynh Anh Hai',             8,  10100000),
('Nguyen Bao Ha',             5,  45000000),
('Pham Gia Ha',               6,  17700000),
('Tran Ngoc Phong',           4,  15100000),
('Hoang Thi Trang',           7,  18800000),
('Vu Minh Hoa',               2,  22500000),
('Hoang Minh Loc',            2,  10800000),
('Do Thanh Khanh',            8,  14800000),
('Vo Hoang Cuong',            4,  17600000),
('Vo Anh Khanh',              4,  18600000),
('Ly Kim Quan',               9,  40600000),
('Vo Thu Trang',              2,  20800000),
('Le Hoang Nhung',            3,  23200000),
('Ngo Hoang Vy',              7,  30300000),
('Duong Thanh Phong',         4,  23700000),
('Duong Quoc Tuan',           1,  12500000),
('Bui Gia Tien',              2,  15400000),
('Dang Bao Hung',             3,  10700000),
('Nguyen Ngoc Hai',           9,  43000000),
('Do Anh Yen',                7,  37100000),
('Ly Duc Trang',              6,  16500000),
('Ngo Ngoc Trung',            7,  22100000),
('Bui Huu Huong',             1,  12500000),
('Duong Thi Linh',            8,  13500000),
('Do Duc Lan',                5,  26000000),
('Nguyen Van Binh',           6,   9200000),
('Vo Kim Vy',                 5,  16800000),
('Tran Gia Hanh',             7,  19700000),
('Tran Huu Hanh',             7,  17200000),
('Tran Thi Khanh',            4,  24700000),
('Dang Minh Khoa',            4,  22100000),
('Dang Van Vy',               3,  19900000),
('Huynh Ngoc Ha',             8,  11800000),
('Do Hoang Khoa',             3,  12300000),
('Ngo Hoang Dat',             3,  19300000),
('Vu Thanh Huong',            8,  11200000),
('Duong Thu Trang',           5,  43800000),
('Bui Kim Phong',             3,  15200000),
('Do Thi Nga',                1,  17900000),
('Ngo Thi Cuong',             1,  11300000),
('Tran Thanh Hanh',           6,  15400000),
('Nguyen Gia Long',           9,  18500000),
('Do Huu Son',                6,  15400000),
('Phan Duc Nam',              9,  41500000),
('Ngo Quoc Khanh',            9,  22400000),
('Ngo Anh Nam',               9,  29800000),
('Ngo Van Dung',              5,  32300000),
('Vu Hoang Vy',               7,  20800000),
('Pham Ngoc Linh',            3,  24000000),
('Vu Duc An',                 7,  35300000);
