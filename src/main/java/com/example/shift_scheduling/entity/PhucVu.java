package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PHUCVU")
public class PhucVu extends NhanVien {
    @Column(name = "level")
    private int capDo;

    public PhucVu(String hoTen, String soDienThoai, String diaChi, Gender gioiTinh, int capDo) {
        super(hoTen, soDienThoai, diaChi, gioiTinh);
        this.capDo = capDo;
    }
}
