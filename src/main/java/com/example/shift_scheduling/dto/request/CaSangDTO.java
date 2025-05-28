package com.example.shift_scheduling.dto.request;

import java.io.Serializable;
import java.util.List;

import com.example.shift_scheduling.entity.ChiTietMon;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CaSangDTO implements Serializable {

    @Min(value = 1, message = "Số lượng đầu bếp ít nhất là 1")
    private Integer slDauBep;

    @Min(value = 1, message = "Số lượng phục vụ ít nhất là 1")
    private Integer slPhucVu;

    @Min(value = 1, message = "Số lượng lễ tân ít nhất là 1")
    private Integer slLeTan;

    @Min(value = 5, message = "Thời gian bắt đầu phải > 4")
    private Double tgBatDau;

    @Min(value = 24, message = "Thời gian kết thúc phải < 24")
    private Double tgKetThuc;

    private List<ChiTietMon> chiTietMon;

}
