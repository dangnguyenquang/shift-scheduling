package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.ChiTietCaDTO;
import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.NhanVienDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;
import com.example.shift_scheduling.entity.ChiTietCa;

import java.util.List;

public interface INhanVienService {
    int addChef(DauBepDTO dto);

    int addWaiter(PhucVuDTO dto);

    int addReceptionist(LeTanDTO dto);

    DauBepDTO getChefById(int id);

    List<DauBepDTO> getAllChefs();

    PhucVuDTO getWaiterById(int id);

    List<PhucVuDTO> getAllWaiters();

    LeTanDTO getReceptionistById(int id);

    List<LeTanDTO> getAllReceptionists();

    NhanVienDTO getStaffById(int id);

    List<NhanVienDTO> getAllStaffs();

    ChiTietCaDTO shiftRegister(Integer maNv, Integer maCa);
    ChiTietCaDTO convertChiTietCaDTO(ChiTietCa chiTietCa);
}
