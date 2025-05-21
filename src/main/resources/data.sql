INSERT INTO Staff (staffName, gender, address, phone, staffType, level, english, exp) VALUES
('Nguyen Van A', 'MALE', 'Hanoi', '0909123456', 'DAUBEP', 5, TRUE, 10),
('Tran Thi B', 'FEMALE', 'HCM', '0912345678', 'PHUCVU', 2, TRUE, 3),
('Le Van C', 'MALE', 'Da Nang', '0923456789', 'LETAN', 3, FALSE, 4),
('Pham Thi D', 'FEMALE', 'Hue', '0934567890', 'DAUBEP', 4, TRUE, 8),
('Hoang Van E', 'MALE', 'Can Tho', '0945678901', 'PHUCVU', 1, FALSE, 2);


INSERT INTO Salary (staffId, month, year, salary) VALUES
(1, 5, 2025, 0),
(2, 5, 2025, 0),
(3, 5, 2025, 0),
(4, 5, 2025, 0),
(5, 5, 2025, 0);

INSERT INTO ShiftType (typeName, chefNums, serveNums, receptNums) VALUES
( 'Ca sang', 2, 3, 1),
( 'Ca trua', 3, 4, 1),
( 'Ca toi', 4, 5, 1),
( 'Ca khuya', 1, 2, 1),
( 'Ca linh dong', 2, 2, 1);

INSERT INTO Shift (shiftTime, shiftTypeId) VALUES
('2025-05-22 08:00:00', 1),
('2025-05-22 12:00:00', 2),
('2025-05-22 18:00:00', 3),
('2025-05-23 08:00:00', 1),
('2025-05-23 18:00:00', 3);

INSERT INTO DetailedShift (shiftId, staffId, status) VALUES
(1, 1, null),
(1, 2, null),
(2, 3, null),
(3, 4, null),
(4, 5, null);

INSERT INTO Food (foodName, foodType, price, description, cookingTime, foodStatus) VALUES
('Mì Ý', 'MON_AU', 100000, 'Mì Ý sốt bò bằm', 20, TRUE),
('Kimchi', 'MON_HAN', 50000, 'Kimchi cay Hàn Quốc', 10, TRUE),
('Sushi', 'MON_NHAT', 120000, 'Sushi cá hồi', 25, TRUE),
('Bún bò Huế', 'MON_VIET', 70000, 'Đặc sản Huế', 30, TRUE),
('Đậu hũ chiên', 'MON_CHAY', 40000, 'Món chay nhẹ nhàng', 15, TRUE);

INSERT INTO DetailedFood (foodType, foodTypeName) VALUES
('MON_AU', 'Món Âu'),
('MON_HAN', 'Món Hàn'),
('MON_NHAT', 'Món Nhật'),
('MON_VIET', 'Món Việt'),
('MON_CHAY', 'Món Chay');

INSERT INTO DetailedStaffFood (staffId, foodType) VALUES
(1, 'MON_AU'),
(1, 'MON_VIET'),
(4, 'MON_HAN'),
(4, 'MON_CHAY'),
(2, null);


INSERT INTO Feedback (feedbackCode, shiftId, staffId, rate, details) VALUES
(1, 1, 1, 5, 'Rất tốt'),
(2, 2, 2, 4, 'Tốt'),
(3, 3, 3, 2, 'Cần cải thiện'),
(4, 4, 4, 3, 'Trung bình'),
(5, 5, 5, 5, 'Xuất sắc');

INSERT INTO TableType ( tbTypeName, seats) VALUES
('Bàn 2 người', 2),
('Bàn 4 người', 4),
('Bàn 6 người', 6),
('Bàn VIP', 10),
('Bàn gia đình', 8);

INSERT INTO `DiningTable` (tableTypeId) VALUES
(1),
(2),
(2),
(3),
(5);

INSERT INTO DetailedTable (tableId, shiftId, customers) VALUES
(1, 1, 2),
(2, 1, 4),
(3, 2, 3),
(4, 3, 6),
(5, 4, 7);
