package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.dto.request.LuongDTO;
import com.example.shift_scheduling.dto.request.NhanVienDTO;
import com.example.shift_scheduling.entity.DauBep;
import com.example.shift_scheduling.entity.Luong;
import com.example.shift_scheduling.entity.NhanVien;
import com.example.shift_scheduling.repository.LuongRepository;
import com.example.shift_scheduling.service.ILuongService;
import com.example.shift_scheduling.util.LoaiNV;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LuongServiceImpl implements ILuongService {

    private final LuongRepository luongRepository;
    private final CaServiceImpl caServiceImpl;
    private final NhanVienServiceImpl nhanVienService;

    @Override
    public void calculateSalary(int month, int year) {
        List<CaThongKeDTO> thongKeList = caServiceImpl.thongKeCa(month, year);

        for (CaThongKeDTO thongKe : thongKeList) {
            NhanVienDTO nhanVien = nhanVienService.getStaffById(thongKe.getEmployeeId());

            float luongCB = nhanVien.getLuongCB();
            float tongLuong = 0.0f;

            tongLuong += luongCB * (thongKe.getCaSang() + thongKe.getCaChieu() + thongKe.getCaToi());

            tongLuong += luongCB * 0.1f * thongKe.getCaToi();

            if (nhanVien.getLoaiNV() == LoaiNV.DAUBEP) {
                tongLuong += luongCB * 0.15f * thongKe.getSoBuoiBuffet();
            }

            if (nhanVien.getLoaiNV() == LoaiNV.PHUCVU|| nhanVien.getLoaiNV() == LoaiNV.LETAN) {
                tongLuong += luongCB * 0.15f * thongKe.getSoBuoiSuKien();
            }

            // Ghi vào DB
            Luong luong = Luong.builder()
                    .maNV(nhanVien.getMaNV())
                    .thang(month)
                    .nam(year)
                    .luong(tongLuong)
                    .build();

            luongRepository.save(luong);
        }
    }

    @Override
    public List<LuongDTO> getAllSalaries(int month, int year) {
        return luongRepository.findAllByThangAndNam(month, year)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public LuongDTO getSalaryByEmployee(int employeeId, int month, int year) {
        return luongRepository.findByMaNVAndThangAndNam(employeeId, month, year)
                .map(this::mapToDTO)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy lương của nhân viên"));
    }

    private LuongDTO mapToDTO(Luong luong) {
        return LuongDTO.builder()
                .maNV(luong.getMaNV())
                .thang(luong.getThang())
                .nam(luong.getNam())
                .luong(luong.getLuong())
                .build();
    }
}
