package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhucVu extends NhanVien {
    private int capDo;

    public PhucVu(int maNV, String hoTen, String soDienThoai, String diaChi, LoaiNV loaiNV, Gender gioiTinh, int capDo) {
        super(maNV, hoTen, soDienThoai, diaChi, loaiNV, gioiTinh);
        this.capDo = capDo;
    }
}
