package com.example.shift_scheduling.strategy;

import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.entity.PhucVu;
import com.example.shift_scheduling.entity.NhanVien;

public class PhucVuSalaryStrategyImpl implements ISalaryStrategy {
    @Override
    public float calculateSalary(NhanVien nhanVien, CaThongKeDTO thongKe) {
        if (!(nhanVien instanceof PhucVu phucVu)) {
            throw new IllegalArgumentException("NhanVien is not a PhucVu");
        }

        float luongCB = phucVu.getLuongCB();
        float tongLuong = luongCB * (thongKe.getCaSang() + thongKe.getCaChieu() + thongKe.getCaToi());
        tongLuong += luongCB * 0.1f * thongKe.getCaToi();
        tongLuong += luongCB * 0.15f * thongKe.getSoBuoiBuffet();

        tongLuong += phucVu.getCapDo() * 150_000;

        return tongLuong;
    }
}
