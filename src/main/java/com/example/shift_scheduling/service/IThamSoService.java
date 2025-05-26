package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.ThamSoDTO;

public interface IThamSoService {
    ThamSoDTO getFirstThamSo();
    ThamSoDTO updateFirstThamSo(ThamSoDTO dto);
}
