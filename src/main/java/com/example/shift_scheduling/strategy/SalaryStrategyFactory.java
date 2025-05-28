package com.example.shift_scheduling.strategy;

import com.example.shift_scheduling.entity.NhanVien;

public class SalaryStrategyFactory {
    public static ISalaryStrategy getStrategy(NhanVien nhanVien) {
        return switch (nhanVien.getLoaiNV()) {
            case DAUBEP -> new DauBepSalaryStrategyImpl();
            case PHUCVU -> new PhucVuSalaryStrategyImpl();
            case LETAN  -> new LeTanSalaryStrategyImpl();
        };
    }
}
