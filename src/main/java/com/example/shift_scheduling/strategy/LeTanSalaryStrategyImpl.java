package com.example.shift_scheduling.strategy;

import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.entity.LeTan;
import com.example.shift_scheduling.entity.NhanVien;

public class LeTanSalaryStrategyImpl implements ISalaryStrategy {
    @Override
    public float calculateSalary(NhanVien nhanVien, CaThongKeDTO thongKe) {
        if (!(nhanVien instanceof LeTan leTan)) {
            throw new IllegalArgumentException("NhanVien is not a LeTan");
        }

        float luongCB = leTan.getLuongCB();
        float tongLuong = luongCB * (thongKe.getCaSang() + thongKe.getCaChieu() + thongKe.getCaToi());
        tongLuong += luongCB * 0.1f * thongKe.getCaToi();
        tongLuong += luongCB * 0.15f * thongKe.getSoBuoiBuffet();

        tongLuong += leTan.isNgoaiNgu() ? tongLuong + 300_000 : 0;

        return tongLuong;
    }
}
