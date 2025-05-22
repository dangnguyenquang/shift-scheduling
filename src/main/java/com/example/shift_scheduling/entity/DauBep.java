package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiMon;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("DAUBEP")
public class DauBep extends NhanVien {

    @Column(name = "exp")
    private int kinhNghiem;

    @ElementCollection(targetClass = LoaiMon.class)
    @CollectionTable(
                name = "DetailedStaffFood",
            joinColumns = @JoinColumn(name = "staffId")
    )
    @Enumerated(EnumType.STRING)

    @Column(name = "foodType")
    private Set<LoaiMon> dsLoaiMonAn;

    public DauBep() {
        super();
    }

    public DauBep(String hoTen, String soDienThoai, String diaChi, Gender gioiTinh, int kinhNghiem, Set<LoaiMon> dsLoaiMonAn) {
        super(hoTen, soDienThoai, diaChi, gioiTinh);
        this.kinhNghiem = kinhNghiem;
        this.dsLoaiMonAn = dsLoaiMonAn;
    }
}
