package com.example.shift_scheduling.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LuongDTO {

    @NotBlank(message = "Mã nhân viên không được để trống")
    private Integer maNV;

    @NotBlank(message = "Năm không được để trống")
    private Integer nam;

    @NotBlank(message = "Tháng không được để trống")
    @Min(1)
    @Max(12)
    private Integer thang;

    @NotBlank(message = "Lương không được để trống")
    private Float luong;
}
