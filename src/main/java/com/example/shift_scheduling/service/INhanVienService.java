package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.*;

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
}
