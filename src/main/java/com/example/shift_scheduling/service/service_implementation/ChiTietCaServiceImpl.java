package com.example.shift_scheduling.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shift_scheduling.dto.request.ChiTietCaDTO;
import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.repository.ChiTietCaRepository;
import com.example.shift_scheduling.service.IChiTietCaService;
import com.example.shift_scheduling.service.INhanVienService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChiTietCaServiceImpl implements IChiTietCaService {

    private final ChiTietCaRepository chiTietCaRepository;
    private final INhanVienService iNhanVienService;

    @Override
    public List<ChiTietCaDTO> getAllDetailedShifts() {
        List<ChiTietCa> chiTietCa = chiTietCaRepository.findAll();

        List<ChiTietCaDTO> chiTietCaDTO = new ArrayList<>();
        ChiTietCaDTO ctcDTOl;
        for (ChiTietCa ctc : chiTietCa) {
            ctcDTOl = iNhanVienService.convertChiTietCaDTO(ctc);
            chiTietCaDTO.add(ctcDTOl);
        }

        return chiTietCaDTO;
    }
}
