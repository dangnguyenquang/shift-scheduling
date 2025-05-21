package com.example.shift_scheduling.dto.request;

import com.example.shift_scheduling.dto.validator.PhoneNumber;
import com.example.shift_scheduling.util.Gender;
import com.example.shift_scheduling.util.LoaiMon;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DauBepDTO implements Serializable {
    private Integer maNV;

    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;

    private Gender gioiTinh;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @NotBlank(message = "Loại nhân viên không được để trống")
    private LoaiNV loaiNV;

    @PhoneNumber(message = "Số điện thoại không hợp lệ")
    private String soDienThoai;

    @NotNull(message = "Kinh nghiệm không được để trống")
    private Integer kinhNghiem;

    @NotNull(message = "Danh sách món ăn không được để null")
    @Size(min = 1, message = "Phải có ít nhất một loại món ăn")
    private Set<LoaiMon> dsLoaiMonAn;
}
