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
public class FeedbackDTO {
    private Integer maFB;

    @NotBlank(message = "Mã ca không được để trống")
    private Integer maCa;

    @NotBlank(message = "Mã nhân viên không được để trống")
    private Integer maNV;

    @NotBlank(message = "Đánh giá không được để trống")
    @Min(0)
    @Max(10)
    private Integer danhGia;

    private String chiTiet;
}
