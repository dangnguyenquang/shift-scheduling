package com.example.shift_scheduling.service;

import java.util.List;

import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.ChiTietCa;

public interface IXepCaService {
    void autoScheduleShift();
    void handleScheduleShift(Ca ca, List<ChiTietCa> dsDangKy);
    void scheduleStaff(Ca ca, List<ChiTietCa> dsDangKy, Integer soLuong); 
}
