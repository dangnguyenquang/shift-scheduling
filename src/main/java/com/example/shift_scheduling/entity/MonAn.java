package com.example.shift_scheduling.entity;

import java.util.List;

import com.example.shift_scheduling.util.LoaiMon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Food")
public class MonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodId")
    private Integer maMonAn;

    @Column(name = "foodName")
    private String tenMonAn;

    @Column(name = "foodType")
    @Enumerated(EnumType.STRING)
    private LoaiMon loaiMon;

    @Column(name = "price")
    private double gia;

    @Column(name = "description")
    private String moTa;

    @Column(name = "cookingTime")
    private int thoiGianNau;

    @Column(name = "foodStatus")
    private boolean trangThai;

}
