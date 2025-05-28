package com.example.shift_scheduling.dto.request;

import java.io.Serializable;
import java.util.List;

import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.util.LoaiCa;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CaChieuDTO implements Serializable{
    

    @Min(value = 1, message = "Số lượng đầu bếp ít nhất là 1")
    private Integer slDauBep;

    @Min(value = 1, message = "Số lượng phục vụ ít nhất là 1")
    private Integer slPhucVu;

    @Min(value = 1, message = "Số lượng lễ tân ít nhất là 1")
    private Integer slLeTan;

    @Min(value = 11, message = "Thời gian bắt đầu phải > 11")
    private Double tgBatDau;

    @Max(value = 17, message = "Thời gian kết thúc phải < 18")
    private Double tgKetThuc;

    @NotNull(message = "Phụ cấp trưa không được null")
    private String phuCapTrua;

    @NotNull(message = "Buffet không được null")
    private String buffet;

    private List<ChiTietMon> chiTietMon;
}
