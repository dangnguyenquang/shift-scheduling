package com.example.shift_scheduling.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ChiTietMonId implements Serializable{
    
    @Column(name = "foodId")
    private Integer foodId;

    @Column(name = "shiftId")
    private Integer shiftId;
}
