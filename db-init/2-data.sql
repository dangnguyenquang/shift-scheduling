SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

INSERT INTO Staff (staffName, gender, address, phone, staffType, level, english, exp, baseSalary) VALUES
('Nguyễn Văn A', 'MALE', 'Hà Nội', '0909123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Phạm Thị D', 'FEMALE', 'Huế', '0934567890', 'DAUBEP', NULL, NULL, 8, 350000),
('Nguyễn Văn F', 'MALE', 'Hà Nội', '0909193456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyễn Văn G', 'MALE', 'Hải Phòng', '0906123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyễn Văn H', 'MALE', 'Nam Định', '0919123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyễn Văn I', 'MALE', 'Phú Yên', '0909113456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyễn Văn J', 'MALE', 'Hà Nội', '0909223456', 'DAUBEP', NULL, NULL, 10, 350000),
('Lê Văn C', 'MALE', 'Đà Nẵng', '0933456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Lê Thị D', 'FEMALE', 'Đà Nẵng', '0943456789', 'LETAN', NULL, TRUE, NULL, 250000),
('Lê Thị E', 'FEMALE', 'Đà Nẵng', '0953456789', 'LETAN', NULL, TRUE, NULL, 250000),
('Lê Thị F', 'FEMALE', 'Đà Nẵng', '0963456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Lê Thị G', 'FEMALE', 'Đà Nẵng', '0973456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Hoàng Văn E', 'MALE', 'Phú Yên', '0855678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoàng Văn F', 'MALE', 'Phú Thọ', '0945678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoàng Văn G', 'MALE', 'Cần Thơ', '0915678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoàng Văn H', 'MALE', 'Cần Thơ', '0915678801', 'PHUCVU', 1, NULL, NULL, 200000),
('Trần Thị B', 'FEMALE', 'TP. Hồ Chí Minh', '0910345678', 'PHUCVU', 2, TRUE, NULL, 200000);

INSERT INTO Salary (salaryId, staffId, month, year, salary) VALUES
(1, 1, 5, 2025, 0),
(2, 2, 5, 2025, 0),
(3, 3, 5, 2025, 0),
(4, 4, 5, 2025, 0),
(5, 5, 5, 2025, 0);

INSERT INTO Parameter (parameterId, chefBaseSalary, waiterBaseSalary, receptionistBaseSalary) VALUES
(1, 350000, 250000, 200000);


INSERT INTO Account (id, username, password, role) VALUES
(1, 'NguyenQuangDang', '$2a$10$IsuPi8GuG0/wSmWBNCqbweyXFCIBBith8DaE7EUHzeVRMieX9kTL6', 'ADMIN'),
(2, 'TruongHoaiBao', '$2a$10$IsuPi8GuG0/wSmWBNCqbweyXFCIBBith8DaE7EUHzeVRMieX9kTL6', 'MANAGER');

INSERT INTO Shift (shiftTime, shiftTypeCode, chefNums, serveNums, receptNums, timeStart, timeEnd, mealAllowance, buffet, overtimePay, events) VALUES
(CURRENT_DATE, 'CASANG', 4, 4, 1, 6, 11, null, null, null, null),
(CURRENT_DATE, 'CACHIEU', 4, 4, 1, 12, 17, "Cơm gà", "Buffet Lẩu", null , null),
(CURRENT_DATE, 'CATOI', 5, 5, 2, 17, 23, null, null, 30000, "Văn nghệ tối");

INSERT INTO DetailedShift (shiftId, staffId, status) VALUES
(1, 1, "CHUAXEP"),
(1, 2, "CHUAXEP"),
(1, 3, "CHUAXEP"),
(1, 4, "CHUAXEP"),
(1, 5, "CHUAXEP"),
(1, 6, "CHUAXEP"),
(1, 8, "CHUAXEP"),
(1, 9, "CHUAXEP"),
(1, 10, "CHUAXEP"),
(1, 13, "CHUAXEP"),
(1, 14, "CHUAXEP"),
(1, 15, "CHUAXEP"),
(1, 16, "CHUAXEP"),
(1, 17, "CHUAXEP"),

(2, 1, "CHUAXEP"),
(2, 2, "CHUAXEP"),
(2, 3, "CHUAXEP"),
(2, 5, "CHUAXEP"),
(2, 6, "CHUAXEP"),
(2, 7, "CHUAXEP"),
(2, 8, "CHUAXEP"),
(2, 9, "CHUAXEP"),
(2, 10, "CHUAXEP"),
(2, 13, "CHUAXEP"),
(2, 14, "CHUAXEP"),
(2, 15, "CHUAXEP"),
(2, 16, "CHUAXEP"),
(2, 17, "CHUAXEP"),

(3, 1, "CHUAXEP"),
(3, 2, "CHUAXEP"),
(3, 3, "CHUAXEP"),
(3, 4, "CHUAXEP"),
(3, 6, "CHUAXEP"),
(3, 7, "CHUAXEP"),
(3, 8, "CHUAXEP"),
(3, 9, "CHUAXEP"),
(3, 10, "CHUAXEP"),
(3, 13, "CHUAXEP"),
(3, 14, "CHUAXEP"),
(3, 15, "CHUAXEP"),
(3, 16, "CHUAXEP"),
(3, 17, "CHUAXEP");

INSERT INTO Food (foodName, foodType, price, description, cookingTime, foodStatus) VALUES
('Mì Ý', 'MON_AU', 100000, 'Mì Ý sốt bò bằm', 20, TRUE),
('Kimchi', 'MON_HAN', 50000, 'Kimchi cay Hàn Quốc', 10, TRUE),
('Sushi', 'MON_NHAT', 120000, 'Sushi cá hồi', 25, TRUE),
('Bún bò Huế', 'MON_VIET', 70000, 'Đặc sản Huế', 30, TRUE),
('Chuột đồng nướng', 'MON_NUONG', 70000, 'Chuột nuôi ngoài đồng', 30, TRUE),
('Lẩu Tứ Xuyên', 'MON_TRUNG', 70000, 'Siêu cay không thể tả', 30, TRUE),
('Pana cotta', 'TRANG_MIENG_AU', 70000, 'Ăn ngon lắm', 30, TRUE),
('Trái cây nhiệt đới', 'TRANG_MIENG_A', 70000, 'Ngon ngọt giải khát', 30, TRUE),
('Đậu hũ chiên', 'MON_CHAY', 40000, 'Món chay nhẹ nhàng', 15, TRUE);

INSERT INTO DetailedFoodType (foodType, foodTypeName) VALUES
('MON_AU', 'Món Âu'),
('MON_HAN', 'Món Hàn'),
('MON_NHAT', 'Món Nhật'),
('MON_VIET', 'Món Việt'),
('MON_TRUNG', 'Món Trung'),
('MON_CHAY', 'Món Chay'),
('MON_NUONG', 'Món Nướng'),
('TRANG_MIENG_AU', 'Tráng miệng âu'),
('TRANG_MIENG_A', 'Tráng miệng á');

INSERT INTO DetailedStaffFood (staffId, foodType) VALUES
(1, 'MON_AU'),
(1, 'MON_VIET'),

(2, 'MON_HAN'),
(2, 'MON_CHAY'),

(3, 'MON_HAN'),
(3, 'MON_NHAT'),

(4, 'MON_AU'),
(4, 'MON_CHAY'),

(5, 'MON_CHAY'),
(5, 'MON_VIET'),

(6, 'MON_TRUNG'),
(6, 'MON_NUONG'),

(7, 'TRANG_MIENG_AU'),
(7, 'TRANG_MIENG_A');

INSERT INTO DetailedShiftFood (foodId, shiftId, quantity) VALUES
(1, 1, 30),
(5, 1, 20),
(2, 2, 20),
(3, 2, 20),
(4, 3, 25),
(5, 3, 20);


INSERT INTO Feedback (feedbackCode, shiftId, staffId, rate, details) VALUES
(1, 1, 1, 5, 'Rất tốt'),
(2, 2, 2, 4, 'Tốt'),
(3, 3, 3, 2, 'Cần cải thiện');

