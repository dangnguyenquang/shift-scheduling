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

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CATOI")
@ToString(callSuper = true)
public class CaToi extends Ca {
    
    @Column(name = "events")
    private String suKien;

    @Column(name = "overtimePay")
    private Double phuCapToi;
}
