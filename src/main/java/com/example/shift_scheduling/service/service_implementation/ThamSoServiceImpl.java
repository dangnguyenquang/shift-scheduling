package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.ThamSoDTO;
import com.example.shift_scheduling.entity.ThamSo;
import com.example.shift_scheduling.exception.InvalidDataException;
import com.example.shift_scheduling.repository.ThamSoRepository;
import com.example.shift_scheduling.service.IThamSoService;
import org.springframework.stereotype.Service;

@Service
public class ThamSoServiceImpl implements IThamSoService {
    private final ThamSoRepository thamSoRepository;

    public ThamSoServiceImpl(ThamSoRepository thamSoRepository) {
        this.thamSoRepository = thamSoRepository;
    }

    @Override
    public ThamSoDTO getFirstThamSo() {
        ThamSo ts = thamSoRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new InvalidDataException("Không tìm thấy tham số hệ thống"));

        return mapToDTO(ts);
    }

    @Override
    public ThamSoDTO updateFirstThamSo(ThamSoDTO dto) {
        ThamSo ts = thamSoRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new InvalidDataException("Không tìm thấy tham số hệ thống"));

        ts.setLuongCBDB(dto.getLuongCBDB());
        ts.setLuongCBLT(dto.getLuongCBLT());
        ts.setLuongCBPV(dto.getLuongCBPV());

        thamSoRepository.save(ts);
        return mapToDTO(ts);
    }

    private ThamSoDTO mapToDTO(ThamSo ts) {
        return ThamSoDTO.builder()
                .luongCBDB(ts.getLuongCBDB())
                .luongCBLT(ts.getLuongCBLT())
                .luongCBPV(ts.getLuongCBPV())
                .build();
    }
}
