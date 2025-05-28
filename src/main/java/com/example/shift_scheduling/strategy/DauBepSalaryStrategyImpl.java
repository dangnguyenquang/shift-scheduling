package com.example.shift_scheduling.strategy;

import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.entity.DauBep;
import com.example.shift_scheduling.entity.NhanVien;

public class DauBepSalaryStrategyImpl implements ISalaryStrategy {
    @Override
    public float calculateSalary(NhanVien nhanVien, CaThongKeDTO thongKe) {
        if (!(nhanVien instanceof DauBep dauBep)) {
            throw new IllegalArgumentException("NhanVien is not a DauBep");
        }

        float luongCB = dauBep.getLuongCB();
        float tongLuong = luongCB * (thongKe.getCaSang() + thongKe.getCaChieu() + thongKe.getCaToi());
        tongLuong += luongCB * 0.1f * thongKe.getCaToi();
        tongLuong += luongCB * 0.15f * thongKe.getSoBuoiBuffet();

        tongLuong += dauBep.getKinhNghiem() * 100_000;

        return tongLuong;
    }
}
