
CREATE TABLE Staff (
    staffId INT AUTO_INCREMENT PRIMARY KEY,
    staffName VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    address VARCHAR(100),
    phone VARCHAR(12),
    staffType ENUM('DAUBEP', 'PHUCVU', 'LETAN') NOT NULL,
    level INT,
    english BOOLEAN,
    exp INT,
    basicSalary FLOAT
);

CREATE TABLE Salary (
    staffId INT,
    month INT,
    year INT,
    salary FLOAT,
    PRIMARY KEY (staffId, month, year),
    FOREIGN KEY (staffId) REFERENCES Staff(staffId)
);


CREATE TABLE Shift (
    shiftId INT AUTO_INCREMENT PRIMARY KEY,
    shiftTime DATE,
    shiftTypeCode ENUM('CASANG', 'CACHIEU', 'CATOI'),
    chefNums INT,
    serveNums INT,
    receptNums INT,
    timeStart FLOAT,
    timeEnd FLOAT,
    mealAllowance VARCHAR(100),
    overtimePay FLOAT,
    buffet VARCHAR(50),
    events VARCHAR(50)
);

CREATE TABLE DetailedShift (
    shiftId INT,
    staffId INT,
    status ENUM('CHUAXEP', 'THATBAI', 'THANHCONG'),
    PRIMARY KEY (shiftId, staffId),
    FOREIGN KEY (shiftId) REFERENCES Shift(shiftId),
    FOREIGN KEY (staffId) REFERENCES Staff(staffId)
);

CREATE TABLE Food (
    foodId INT AUTO_INCREMENT PRIMARY KEY,
    foodName VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    foodType ENUM(
        'MON_AU',
        'MON_HAN',
        'MON_NHAT',
        'MON_TRUNG',
        'MON_VIET',
        'MON_CHAY',
        'MON_NUONG',
        'TRANG_MIEN_AU',
        'TRANG_MIEN_A'
    ),
    price FLOAT,
    description VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    cookingTime INT,
    foodStatus BOOLEAN,
    INDEX idx_foodType(foodType)
);


CREATE TABLE DetailedStaffFood (
    staffId INT,
    foodType ENUM(
        'MON_AU',
        'MON_HAN',
        'MON_NHAT',
        'MON_TRUNG',
        'MON_VIET',
        'MON_CHAY',
        'MON_NUONG',
        'TRANG_MIEN_AU',
        'TRANG_MIEN_A'
    ),

    FOREIGN KEY (staffId) REFERENCES Staff(staffId),
    FOREIGN KEY (foodType) REFERENCES Food(foodType)
);

CREATE Table DetailedShiftFood (
    shiftFoodId INT AUTO_INCREMENT PRIMARY KEY,
    foodId INT,
    shiftId INT,
    FOREIGN KEY (foodId) REFERENCES Food(foodId),
    FOREIGN KEY (shiftId) REFERENCES Shift(shiftId),
    quantity INT
);

CREATE TABLE DetailedFoodType (
    foodType ENUM(
        'MON_AU',
        'MON_HAN',
        'MON_NHAT',
        'MON_TRUNG',
        'MON_VIET',
        'MON_CHAY',
        'MON_NUONG',
        'TRANG_MIEN_AU',
        'TRANG_MIEN_A'
    ) PRIMARY KEY,
    foodTypeName VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
);

CREATE TABLE Feedback (
    feedbackCode INT AUTO_INCREMENT PRIMARY KEY,
    shiftId INT,
    staffId INT,
    rate INT,
    details VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    FOREIGN KEY (shiftId) REFERENCES Shift(shiftId),
    FOREIGN KEY (staffId) REFERENCES Staff(staffId)
);

CREATE TABLE TableType (
    tableTypeId INT AUTO_INCREMENT PRIMARY KEY,
    tbTypeName VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    seats INT
);

CREATE TABLE DiningTable (
    tableId INT AUTO_INCREMENT PRIMARY KEY,
    tableTypeId INT,
    FOREIGN KEY (tableTypeId) REFERENCES TableType(tableTypeId)
);

CREATE TABLE DetailedTable (
    tableId INT,
    shiftId INT,
    customers INT,
    PRIMARY KEY (tableId, shiftId),
    FOREIGN KEY (tableId) REFERENCES DiningTable(tableId),
    FOREIGN KEY (shiftId) REFERENCES Shift(shiftId)
);

