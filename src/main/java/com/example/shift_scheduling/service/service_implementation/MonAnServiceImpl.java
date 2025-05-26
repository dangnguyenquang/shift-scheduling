package com.example.shift_scheduling.service.service_implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shift_scheduling.dto.request.MonAnDTO;
import com.example.shift_scheduling.entity.MonAn;
import com.example.shift_scheduling.repository.MonAnRepository;
import com.example.shift_scheduling.service.IMonAnService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MonAnServiceImpl implements IMonAnService {

    private final MonAnRepository monAnRepository;

    @Autowired
    public MonAnServiceImpl(MonAnRepository monAnRepository) {
        this.monAnRepository = monAnRepository;
    }

    @Override
    public List<MonAn> getAllFood() {
        return monAnRepository.findAll();
    }

    @Override
    public MonAn saveFood(MonAnDTO request) {
        MonAn monAn = MonAn.builder()
                .tenMonAn(request.getTenMonAn())
                .loaiMon(request.getLoaiMon())
                .gia(request.getGia())
                .moTa(request.getMoTa())
                .thoiGianNau(request.getThoiGianNau())
                .trangThai(request.isTrangThai())
                .build();
        monAnRepository.save(monAn);
        return monAn;
    }

    @Override
    public MonAn getFoodById(Integer id) {
        return monAnRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Food not found in DB!"));
    }

    @Override
    public MonAn updateFood(Integer id, MonAnDTO request) {
        MonAn monAn = getFoodById(id);
        monAn.setTenMonAn(request.getTenMonAn());
        monAn.setLoaiMon(request.getLoaiMon());
        monAn.setGia(request.getGia());
        monAn.setMoTa(request.getMoTa());
        monAn.setThoiGianNau(request.getThoiGianNau());
        monAn.setTrangThai(request.isTrangThai());
        monAnRepository.save(monAn);

        return monAn;
    }

    @Override
    public void deleteFood(Integer id) {
        MonAn monAn = getFoodById(id);
        if (monAn != null) {
            monAnRepository.deleteById(id);
        }
    }

    @Override
    public void updateFoodStatus(Integer id, boolean status) {
        MonAn monAn = getFoodById(id);
        monAn.setTrangThai(status);
        monAnRepository.save(monAn);
    }

}
