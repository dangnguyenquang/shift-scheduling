package com.example.shift_scheduling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("CACHIEU")
@Entity
@ToString(callSuper = true)
public class CaChieu extends Ca {
    
    @Column(name = "buffet")
    private String buffet;

    @Column(name = "mealAllowance")
    private String phuCapTrua;
}
