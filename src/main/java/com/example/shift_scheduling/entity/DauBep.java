package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiMon;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class DauBep extends NhanVien {

    private int kinhNghiem;

    @ElementCollection(targetClass = LoaiMon.class)
    @CollectionTable(
            name = "nhanvien_loaimon",
            joinColumns = @JoinColumn(name = "manhanvien")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "maloaimon")
    private Set<LoaiMon> dsLoaiMonAn;

    public DauBep(int maNV, String hoTen, String soDienThoai, String diaChi, LoaiNV loaiNV, Gender gioiTinh, int kinhNghiem, Set<LoaiMon> dsLoaiMonAn) {
        super(maNV, hoTen, soDienThoai, diaChi, loaiNV, gioiTinh);
        this.kinhNghiem = kinhNghiem;
        this.dsLoaiMonAn = dsLoaiMonAn;
    }
}
