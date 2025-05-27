package com.example.shift_scheduling.dto.request;

import com.example.shift_scheduling.dto.validator.PhoneNumber;
import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVienDTO implements Serializable {
    private Integer maNV;

    private float luongCB;

    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;

    private Gender gioiTinh;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @PhoneNumber(message = "Số điện thoại không hợp lệ")
    private String soDienThoai;

    private LoaiNV loaiNV;
}
