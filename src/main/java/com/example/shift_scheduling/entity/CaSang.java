package com.example.shift_scheduling.entity;

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
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("CASANG")
@SuperBuilder
@NoArgsConstructor
public class CaSang extends Ca {
    
}
