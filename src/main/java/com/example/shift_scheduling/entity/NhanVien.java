package com.example.shift_scheduling.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staffType", discriminatorType = DiscriminatorType.STRING)
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffId")
    private int maNV;

    @Column(name = "staffName")
    private String hoTen;

    @Column(name = "phone")
    private String soDienThoai;

    @Column(name = "address")
    private String diaChi;

    @Column(name = "baseSalary")
    private Float luongCB;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gioiTinh;

    @Column(name = "staffType", insertable = false, updatable = false)
    private LoaiNV loaiNV;

    public NhanVien() {
    }

    public NhanVien(String hoTen, String soDienThoai, String diaChi, Float luongCB, Gender gioiTinh) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.luongCB = luongCB;
        this.gioiTinh = gioiTinh;
    }
    
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietCa> dsCaLamViec = new ArrayList<>();

    // public Integer getCaTrongTuan() {
    //     if (dsCaLamViec != null) {
    //         dsCaLamViec.stream()
    //                 .filter(null)
    //     }
    // }
}
