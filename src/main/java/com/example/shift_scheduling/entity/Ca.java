package com.example.shift_scheduling.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.shift_scheduling.util.LoaiCa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "Shift")
@Table(name = "Shift")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shiftTypeCode", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "loaiCa")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CaSang.class, name = "CASANG"),
        @JsonSubTypes.Type(value = CaChieu.class, name = "CACHIEU"),
        @JsonSubTypes.Type(value = CaToi.class, name = "CATOI")
})
public abstract class Ca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shiftId")
    private Integer id;

    @Column(name = "shiftTime")
    private LocalDate ngayCong;

    @JsonProperty("loaiCa")
    @Enumerated(EnumType.STRING)
    @Column(name = "shiftTypeCode", insertable = false, updatable = false)
    private LoaiCa loaiCa;

    @JsonProperty("slDauBep")
    @Column(name = "chefNums")
    private Integer slDauBep;

    @Column(name = "serveNums")
    private Integer slPhucVu;

    @Column(name = "receptNums")
    private Integer slLeTan;

    @Column(name = "timeStart")
    private Double tgBatDau;

    @Column(name = "timeEnd")
    private Double tgKetThuc;

    @OneToMany(mappedBy = "ca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietMon> chiTietMon = new ArrayList<>();


}
