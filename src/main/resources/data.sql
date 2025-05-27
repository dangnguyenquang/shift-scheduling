INSERT INTO Staff (staffName, gender, address, phone, staffType, level, english, exp, baseSalary) VALUES
('Nguyen Van A', 'MALE', 'Hanoi', '0909123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Pham Thi D', 'FEMALE', 'Hue', '0934567890', 'DAUBEP', NULL, NULL, 8, 350000),
('Nguyen Van F', 'MALE', 'Hanoi', '0909193456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyen Van G', 'MALE', 'HaiPhong', '0906123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyen Van H', 'MALE', 'NamDinh', '0919123456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyen Van I', 'MALE', 'PhuYen', '0909113456', 'DAUBEP', NULL, NULL, 10, 350000),
('Nguyen Van J', 'MALE', 'Hanoi', '0909223456', 'DAUBEP', NULL, NULL, 10, 350000),
('Le Van C', 'MALE', 'Da Nang', '0933456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Le Thi D', 'FEMALE', 'Da Nang', '0943456789', 'LETAN', NULL, TRUE, NULL, 250000),
('Le Thi E', 'FEMALE', 'Da Nang', '0953456789', 'LETAN', NULL, TRUE, NULL, 250000),
('Le Thi F', 'FEMALE', 'Da Nang', '0963456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Le Thi G', 'FEMALE', 'Da Nang', '0973456789', 'LETAN', NULL, FALSE, NULL, 250000),
('Hoang Van E', 'MALE', 'PhuYen', '0855678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoang Van F', 'MALE', 'Phu Tho', '0945678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoang Van G', 'MALE', 'Can Tho', '0915678901', 'PHUCVU', 1, NULL, NULL, 200000),
('Hoang Van H', 'MALE', 'Can Tho', '0915678801', 'PHUCVU', 1, NULL, NULL, 200000),
('Tran Thi B', 'FEMALE', 'HCM', '0910345678', 'PHUCVU', 2, TRUE, NULL, 200000);

INSERT INTO Salary (salaryId, staffId, month, year, salary) VALUES
(1, 1, 5, 2025, 0),
(2, 2, 5, 2025, 0),
(3, 3, 5, 2025, 0),
(4, 4, 5, 2025, 0),
(5, 5, 5, 2025, 0);

INSERT INTO Parameter (parameterId, chefBaseSalary, waiterBaseSalary, receptionistBaseSalary) VALUES
(1, 350000, 250000, 200000);


INSERT INTO Account (id, username, password) VALUES
(1, 'NguyenQuangDang', '$2a$10$IsuPi8GuG0/wSmWBNCqbweyXFCIBBith8DaE7EUHzeVRMieX9kTL6'),
(2, 'TruongHoaiBao', '$2a$10$IsuPi8GuG0/wSmWBNCqbweyXFCIBBith8DaE7EUHzeVRMieX9kTL6');

INSERT INTO Shift (shiftTime, shiftTypeCode, chefNums, serveNums, receptNums, timeStart, timeEnd, mealAllowance, buffet, overtimePay, events) VALUES
('2025-05-24', 'CASANG', 10, 10, 2, 6, 11, null, null, null, null),
('2025-05-24', 'CACHIEU', 10, 10, 2, 12, 17, "COM_GA", "BUFFET_LAU", null , null),
('2025-05-24', 'CATOI', 12, 12, 4, 17, 23, null, null, 30000, "ACOUSTIC");

INSERT INTO DetailedShift (shiftId, staffId, status) VALUES
(1, 1, null),
(1, 2, null),
(2, 3, null),
(3, 4, null),
(3, 5, null);

INSERT INTO Food (foodName, foodType, price, description, cookingTime, foodStatus) VALUES
('Mì Ý', 'MON_AU', 100000, 'Mì Ý sốt bò bằm', 20, TRUE),
('Kimchi', 'MON_HAN', 50000, 'Kimchi cay Hàn Quốc', 10, TRUE),
('Sushi', 'MON_NHAT', 120000, 'Sushi cá hồi', 25, TRUE),
('Bún bò Huế', 'MON_VIET', 70000, 'Đặc sản Huế', 30, TRUE),
('Chuột đồng nướng', 'MON_NUONG', 70000, 'Chuột nuôi ngoài đồng', 30, TRUE),
('Lẩu Tứ Xuyên', 'MON_TRUNG', 70000, 'Siêu cay không thể tả', 30, TRUE),
('Pana cotta', 'TRANG_MIENG_AU', 70000, 'Ăn ngon lắm', 30, TRUE),
('Trái cây nhiệt đới', 'TRANG_MIENG_A', 70000, 'Ngon ngọt giải khát', 30, TRUE),=
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
(1, 1, 10),
(2, 1, 10),
(1, 2, 8),
(5, 2, 2),
(2, 3, 5);


INSERT INTO Feedback (feedbackCode, shiftId, staffId, rate, details) VALUES
(1, 1, 1, 5, 'Rất tốt'),
(2, 2, 2, 4, 'Tốt'),
(3, 3, 3, 2, 'Cần cải thiện');


INSERT INTO TableType ( tbTypeName, seats) VALUES
('Bàn 2 người', 2),
('Bàn 4 người', 4),
('Bàn 6 người', 6),
('Bàn VIP', 10),
('Bàn gia đình', 8);

INSERT INTO `DiningTable` (tableTypeId) VALUES
(1),
(2),
(2);


INSERT INTO DetailedTable (tableId, shiftId, customers) VALUES
(1, 1, 2),
(2, 1, 4),
(3, 2, 3);

