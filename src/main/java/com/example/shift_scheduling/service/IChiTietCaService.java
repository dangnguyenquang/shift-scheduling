package com.example.shift_scheduling.service;

import java.util.List;

import com.example.shift_scheduling.dto.request.ChiTietCaDTO;

public interface IChiTietCaService {
    
        public List<ChiTietCaDTO> getAllDetailedShifts();

}
