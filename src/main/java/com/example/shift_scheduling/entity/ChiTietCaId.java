package com.example.shift_scheduling.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ChiTietCaId implements Serializable {

    @Column(name = "staffId")
    private Integer staffId;

    @Column(name = "shiftId")
    private Integer shiftId;

}
