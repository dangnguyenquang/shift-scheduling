package com.example.shift_scheduling.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CaThongKeDTO {
    private int employeeId;
    private int caSang;
    private int caChieu;
    private int caToi;
    private int soBuoiBuffet;
    private int soBuoiSuKien;
}
