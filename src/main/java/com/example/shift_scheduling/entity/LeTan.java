package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;

public class LeTan extends NhanVien {
    private boolean ngoaiNgu;

    public LeTan(boolean ngoaiNgu) {
        this.ngoaiNgu = ngoaiNgu;
    }

    public LeTan(String hoTen, String soDienThoai, String diaChi, Gender gioiTinh, boolean ngoaiNgu) {
        super(hoTen, soDienThoai, diaChi, gioiTinh);
        this.ngoaiNgu = ngoaiNgu;
    }

    public boolean isNgoaiNgu() {
        return ngoaiNgu;
    }

    public void setNgoaiNgu(boolean ngoaiNgu) {
        this.ngoaiNgu = ngoaiNgu;
    }
}
