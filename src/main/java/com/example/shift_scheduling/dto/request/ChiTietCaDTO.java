package com.example.shift_scheduling.dto.request;

import java.time.LocalDate;

import com.example.shift_scheduling.util.LoaiCa;
import com.example.shift_scheduling.util.TrangThaiXepCa;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChiTietCaDTO {
    private Integer maNv;
    private String tenNhanVien;
    private Integer maCa;
    private LoaiCa loaiCa;
    private LocalDate ngayCong;
    private TrangThaiXepCa trangThaiXepCa;
}
