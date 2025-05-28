package com.example.shift_scheduling.strategy;

import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.entity.NhanVien;

public interface ISalaryStrategy {
    float calculateSalary(NhanVien nhanVien, CaThongKeDTO thongKe);
}
