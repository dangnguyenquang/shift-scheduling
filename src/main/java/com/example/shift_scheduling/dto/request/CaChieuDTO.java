package com.example.shift_scheduling.dto.request;

import java.io.Serializable;
import java.util.List;

import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.util.LoaiCa;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CaChieuDTO implements Serializable{
    

    @Min(value = 1, message = "So luong dau bep it nhat la 1")
    private Integer slDauBep;

    @Min(value = 1, message = "So luong phuc vu it nhat la 1")
    private Integer slPhucVu;

    @Min(value = 1, message = "So luong le tan it nhat la 1")
    private Integer slLeTan;

    @Min(value = 5, message = "Thoi gian bat dau phai lon hon 4")
    private Double tgBatDau;

    @Min(value = 24, message = "Thoi gian ket thuc phai nho hon 24")
    private Double tgKetThuc;

    private String phuCapTrua;

    private String buffet;

    private List<ChiTietMon> chiTietMon;
}
