package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;

public interface INhanVienService {
    int addChef(DauBepDTO dto);
    int addWaiter(PhucVuDTO dto);
    int addReceptionist(LeTanDTO dto);
}
