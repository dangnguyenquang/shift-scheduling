package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.LuongDTO;
import com.example.shift_scheduling.entity.Luong;
import com.example.shift_scheduling.repository.LuongRepository;
import com.example.shift_scheduling.service.ILuongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LuongServiceImpl implements ILuongService {

    private final LuongRepository luongRepository;

    public LuongServiceImpl(LuongRepository luongRepository) {
        this.luongRepository = luongRepository;
    }

    @Override
    public void calculateSalary(int month, int year) {
        // TODO: Implement logic for calculating salary based on shift, attendance, etc.
        // This could involve updating database records for salaries.
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
