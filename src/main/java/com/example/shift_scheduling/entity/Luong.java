package com.example.shift_scheduling.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Salary")
public class Luong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    private int maLuong;

    @Column(name = "staffId")
    private int maNV;

    @Column(name = "month")
    private int thang;

    @Column(name = "year")
    private int nam;

    @Column(name = "salary")
    private float luong;

    public Luong() {
    }

    @Builder
    public Luong(int maNV, int thang, int nam, float luong) {
        this.maNV = maNV;
        this.thang = thang;
        this.nam = nam;
        this.luong = luong;
    }
}
