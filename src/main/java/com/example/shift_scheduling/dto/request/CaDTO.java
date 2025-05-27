package com.example.shift_scheduling.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CaDTO {
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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Min(value = 0, message = "Phu cap tien OT phai lon hon 0")
    private Double phuCapToi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String suKien;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phuCapTrua;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String buffet;

    private List<ChiTietMonDTO> chiTietMon;
}
