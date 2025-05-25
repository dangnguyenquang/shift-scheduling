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
@DiscriminatorValue("LETAN")
public class LeTan extends NhanVien {
    @Column(name = "english")
    private boolean ngoaiNgu;

    public LeTan() {
    }

    public LeTan(String hoTen, String soDienThoai, String diaChi, Float luongCB, Gender gioiTinh, boolean ngoaiNgu) {
        super(hoTen, soDienThoai, diaChi, luongCB, gioiTinh);
        this.ngoaiNgu = ngoaiNgu;
    }
}
