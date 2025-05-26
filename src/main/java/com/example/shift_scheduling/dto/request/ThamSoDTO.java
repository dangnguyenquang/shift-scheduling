package com.example.shift_scheduling.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThamSoDTO {
    private Integer maTS;

    @Min(0)
    private Float luongCBDB;

    @Min(0)
    private Float luongCBPV;

    @Min(0)
    private Float luongCBLT;
}
