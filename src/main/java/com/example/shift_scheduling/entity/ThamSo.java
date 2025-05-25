package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Parameter")
public class ThamSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameterId")
    private int maTS;

    @Column(name = "chefBaseSalary")
    private Float luongCBDB;

    @Column(name = "waiterBaseSalary")
    private Float luongCBPV;

    @Column(name = "receptionistBaseSalary")
    private Float luongCBLT;

    public ThamSo() {
    }

    public ThamSo(Float luongCBDB, Float luongCBPV, Float luongCBLT) {
        this.luongCBDB = luongCBDB;
        this.luongCBPV = luongCBPV;
        this.luongCBLT = luongCBLT;
    }
}
