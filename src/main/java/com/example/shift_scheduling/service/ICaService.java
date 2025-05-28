package com.example.shift_scheduling.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shift_scheduling.dto.request.CaChieuDTO;
import com.example.shift_scheduling.dto.request.CaSangDTO;
import com.example.shift_scheduling.dto.request.CaToiDTO;
import com.example.shift_scheduling.dto.request.ChiTietCaDTO;
import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.CaChieu;
import com.example.shift_scheduling.entity.CaSang;
import com.example.shift_scheduling.entity.CaToi;
import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.util.LoaiCa;

@Service
public interface ICaService {
    public List<Ca> getShiftTemplateJSon();

    public void autoGenerateShift(LocalDate date, Integer dayNums);

    public LoaiCa getTypeCode(Ca ca);

    public Ca saveShift(Ca ca);

    public List<Ca> getAllShifts();

    public void deleteShift(Integer id);
    
    public Ca findShiftById(Integer id);

    public Ca findDetailedShift(LocalDate date, LoaiCa loaiCa);

    public CaSang updateMorningShift(LocalDate date, CaSangDTO  caSangDTO);

    public CaChieu updateAfternoonShift(LocalDate date, CaChieuDTO caChieuDTO );

    public CaToi updateEveningShift(LocalDate date, CaToiDTO caToiDTO);
}
