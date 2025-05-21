package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LeTan extends NhanVien {
    private boolean ngoaiNgu;

    public LeTan(int maNV, String hoTen, String soDienThoai, String diaChi, LoaiNV loaiNV, Gender gioiTinh, boolean ngoaiNgu) {
        super(maNV, hoTen, soDienThoai, diaChi, loaiNV, gioiTinh);
        this.ngoaiNgu = ngoaiNgu;
    }
}
