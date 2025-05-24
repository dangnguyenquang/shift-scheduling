package com.example.shift_scheduling.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackCode")
    private int maFB;

    @Column(name = "shiftId")
    private int maCa;

    @Column(name = "staffId")
    private int maNV;

    @Column(name = "rate")
    private int danhGia;

    @Column(name = "details")
    private String chiTiet;

    public Feedback() {
    }

    public Feedback(int maCa, int maNV, int danhGia, String chiTiet) {
        this.maCa = maCa;
        this.maNV = maNV;
        this.danhGia = danhGia;
        this.chiTiet = chiTiet;
    }
}
