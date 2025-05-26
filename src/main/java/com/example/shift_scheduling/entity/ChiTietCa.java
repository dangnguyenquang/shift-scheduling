package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.TrangThaiXepCa;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "DetailedShift")
public class ChiTietCa {

    @EmbeddedId
    private ChiTietCaId ctId;

    @ManyToOne
    @MapsId("staffId")
    @JoinColumn(name = "staffId")
    private NhanVien nhanVien;

    @ManyToOne
    @MapsId("shiftId")
    @JoinColumn(name = "shiftId")
    private Ca ca;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TrangThaiXepCa ttXepCa;

}
