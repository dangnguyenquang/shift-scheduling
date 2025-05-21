package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNV;

    private String hoTen;
    private String soDienThoai;
    private String diaChi;

    @Enumerated(EnumType.STRING)
    private LoaiNV loaiNV;

    @Enumerated(EnumType.STRING)
    private Gender gioiTinh;

    public NhanVien(int maNV, String hoTen, String soDienThoai, String diaChi, LoaiNV loaiNV, Gender gioiTinh) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.loaiNV = loaiNV;
        this.gioiTinh = gioiTinh;
    }
}
