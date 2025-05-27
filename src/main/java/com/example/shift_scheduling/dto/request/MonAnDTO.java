package com.example.shift_scheduling.dto.request;

import java.io.Serializable;

import com.example.shift_scheduling.util.LoaiMon;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonAnDTO implements Serializable {
    @NotBlank(message = "Ten mon an khong duoc de trong")
    private String tenMonAn;

    public LoaiMon loaiMon;

    @Min(0)
    private double gia;

    @NotNull(message = "Mo ta khong duoc null")
    private String moTa;

    @Min(value = 1, message = "Thoi gian nau phai lon hon 1phut")
    @NotNull(message = "Thoi gian nau khong duoc de trong")
    private int thoiGianNau;

    private boolean trangThai;

}
