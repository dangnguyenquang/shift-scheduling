package com.example.shift_scheduling.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DetailedShiftFood")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChiTietMon {

    @EmbeddedId
    @JsonIgnore
    private ChiTietMonId id;

    @ManyToOne
    @JoinColumn(name = "shiftId")
    @MapsId("shiftId")
    @JsonIgnore
    private Ca ca;

    @ManyToOne
    @JoinColumn(name = "foodId")
    @MapsId("foodId")
    @JsonIgnore
    private MonAn monAn;

    @Column(name = "quantity")
    private Integer soLuong;

    @Transient
    @JsonProperty("maMonAn")
    private Integer maMonAn;

    @JsonProperty("maMonAn")
    public void setMaMonAn(Integer maMonAn) {
        this.maMonAn = maMonAn;
        if (maMonAn != null) {
            this.monAn = new MonAn();
            this.monAn.setMaMonAn(maMonAn);
        }
    }

    public Integer getMaMonAn() {
        return monAn != null ? monAn.getMaMonAn() : null;
    }

}