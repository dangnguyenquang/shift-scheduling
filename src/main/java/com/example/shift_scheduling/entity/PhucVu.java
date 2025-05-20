package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import jakarta.persistence.Entity;

@Entity
public class PhucVu extends NhanVien {
    private int capDo;

    public PhucVu(int capDo) {
        this.capDo = capDo;
    }

    public PhucVu(String hoTen, String soDienThoai, String diaChi, Gender gioiTinh, int capDo) {
        super(hoTen, soDienThoai, diaChi, gioiTinh);
        this.capDo = capDo;
    }

    public int getCapDo() {
        return capDo;
    }

    public void setCapDo(int capDo) {
        this.capDo = capDo;
    }
}
