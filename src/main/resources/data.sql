INSERT INTO Staff (staffName, gender, address, phone, staffType, level, english, exp, baseSalary) VALUES
('Nguyen Van A', 'MALE', 'Hanoi', '0909123456', 'DAUBEP', 5, TRUE, 10, 350000),
('Tran Thi B', 'FEMALE', 'HCM', '0912345678', 'PHUCVU', 2, TRUE, 3, 200000),
('Le Van C', 'MALE', 'Da Nang', '0923456789', 'LETAN', 3, FALSE, 4, 250000),
('Pham Thi D', 'FEMALE', 'Hue', '0934567890', 'DAUBEP', 4, TRUE, 8, 350000),
('Hoang Van E', 'MALE', 'Can Tho', '0945678901', 'PHUCVU', 1, FALSE, 2, 200000);


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
('Đậu hũ chiên', 'MON_CHAY', 40000, 'Món chay nhẹ nhàng', 15, TRUE);

INSERT INTO DetailedFoodType (foodType, foodTypeName) VALUES
('MON_AU', 'Món Âu'),
('MON_HAN', 'Món Hàn'),
('MON_NHAT', 'Món Nhật'),
('MON_VIET', 'Món Việt'),
('MON_TRUNG', 'Món Trung'),
('MON_CHAY', 'Món Chay'),
('MON_NUONG', 'Món Nướng'),
('TRANG_MIEN_AU', 'Tráng miệng âu'),
('TRANG_MIEN_A', 'Tráng miệng á');

INSERT INTO DetailedStaffFood (staffId, foodType) VALUES
(1, 'MON_AU'),
(1, 'MON_VIET'),
(4, 'MON_HAN'),
(4, 'MON_CHAY'),
(2, null);

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

