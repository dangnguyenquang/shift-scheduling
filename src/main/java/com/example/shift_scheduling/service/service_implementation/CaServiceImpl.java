package com.example.shift_scheduling.service.service_implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.shift_scheduling.dto.request.CaChieuDTO;
import com.example.shift_scheduling.dto.request.CaSangDTO;
import com.example.shift_scheduling.dto.request.CaToiDTO;
import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.CaChieu;
import com.example.shift_scheduling.entity.CaSang;
import com.example.shift_scheduling.entity.CaToi;
import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.entity.MonAn;
import com.example.shift_scheduling.repository.CaChieuRepository;
import com.example.shift_scheduling.repository.CaRepository;
import com.example.shift_scheduling.repository.CaSangRepository;
import com.example.shift_scheduling.repository.CaToiRepository;
import com.example.shift_scheduling.repository.ChiTietMonRepository;
import com.example.shift_scheduling.service.ICaService;
import com.example.shift_scheduling.service.IMonAnService;
import com.example.shift_scheduling.util.LoaiCa;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaServiceImpl implements ICaService {
    private final CaRepository caRepository;
    private final ChiTietMonRepository chiTietMonRepository;
    private final IMonAnService iMonAnService;
    private final CaSangRepository caSangRepository;
    private final CaChieuRepository caChieuRepository;
    private final CaToiRepository caToiRepository;

    @Override
    public List<Ca> getShiftTemplateJSon() {
        ObjectMapper obj = new ObjectMapper();
        try {
            List<Ca> ds = List.of(obj.readValue(
                    new ClassPathResource("shift-template.json").getInputStream(),
                    Ca[].class));
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void autoGenerateShift(LocalDate date, Integer dayNums) {
        LocalDate maxDate = caRepository.findMaxDate();
        if (date.isBefore(maxDate) || date.isAfter(maxDate.plusDays(1))) {
            throw new RuntimeException("Ngay bat dau phai la: " + maxDate.plusDays(1));
        }
        List<Ca> MauCa = getShiftTemplateJSon();
        List<Ca> autoGenerate = new ArrayList<>();
        log.info("Test data: " + MauCa);
        for (int i = 0; i < dayNums; i++) {
            LocalDate current = date.plusDays(i);

            for (Ca ca : MauCa) {
                if (caRepository.existsByNgayCongAndLoaiCa(current, getTypeCode(ca))) {
                    throw new RuntimeException(
                            getTypeCode(ca) + " ngay " + current + " da ton tai");
                }
                Ca saveShift = saveShift(ca);
                saveShift.setNgayCong(current);
                autoGenerate.add(saveShift);

            }
        }
        caRepository.saveAll(autoGenerate);
    }

    public LoaiCa getTypeCode(Ca ca) {
        if (ca instanceof CaChieu)
            return LoaiCa.CACHIEU;
        if (ca instanceof CaToi)
            return LoaiCa.CATOI;
        return LoaiCa.CASANG;
    }

    public Ca saveShift(Ca ca) {
        Ca saveShift;
        if (ca instanceof CaToi caToi) {
            saveShift = CaToi.builder()
                    .slDauBep(caToi.getSlDauBep())
                    .slPhucVu(caToi.getSlPhucVu())
                    .slLeTan(caToi.getSlLeTan())
                    .tgBatDau(caToi.getTgBatDau())
                    .tgKetThuc(caToi.getTgKetThuc())
                    .phuCapToi(caToi.getPhuCapToi())
                    .suKien(caToi.getSuKien())
                    .build();
        } else if (ca instanceof CaChieu caChieu) {
            saveShift = CaChieu.builder()
                    .slDauBep(caChieu.getSlDauBep())
                    .slPhucVu(caChieu.getSlPhucVu())
                    .slLeTan(caChieu.getSlLeTan())
                    .tgBatDau(caChieu.getTgBatDau())
                    .tgKetThuc(caChieu.getTgKetThuc())
                    .phuCapTrua(caChieu.getPhuCapTrua())
                    .buffet(caChieu.getBuffet())
                    .build();
        } else {
            saveShift = CaSang.builder()
                    .slDauBep(ca.getSlDauBep())
                    .slPhucVu(ca.getSlPhucVu())
                    .slLeTan(ca.getSlLeTan())
                    .tgBatDau(ca.getTgBatDau())
                    .tgKetThuc(ca.getTgKetThuc())
                    .build();
        }
        caRepository.save(saveShift);
        if (ca.getChiTietMon() != null) {
            List<ChiTietMon> chiTietMons = new ArrayList<>();
            for (ChiTietMon ct : ca.getChiTietMon()) {
                Integer maMonAn = ct.getMaMonAn();
                MonAn monAn = iMonAnService.getFoodById(maMonAn);

                ChiTietMon chiTietMon = ChiTietMon.builder()
                        .ca(saveShift)
                        .monAn(monAn)
                        .soLuong(ct.getSoLuong())
                        .build();

                chiTietMons.add(chiTietMon);
            }

            chiTietMonRepository.saveAll(chiTietMons);
            saveShift.setChiTietMon(chiTietMons);
        }

        return saveShift;
    }

    @Override
    public List<Ca> getAllShifts() {
        List<Ca> allShift = caRepository.findAll();
        return allShift;
    }

    @Override
    public void deleteShift(Integer id) {
        Ca delete = findShiftById(id);
        if (delete != null) {
            caRepository.deleteById(id);
        }
    }

    @Override
    public Ca findShiftById(Integer id) {
        return caRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shift not found!"));
    }

    @Override
    public Ca findDetailedShift(LocalDate date, LoaiCa loaiCa) {
        Ca ca = caRepository.findByNgayCongAndLoaiCa(date, loaiCa);
        if (ca != null) {
            return ca;
        } else {
            throw new EntityNotFoundException("Shift not found!");
        }

    }

    @Override
    public CaSang updateMorningShift(LocalDate date, CaSangDTO caSangDTO) {
        CaSang caSang = caSangRepository.findByNgayCongAndLoaiCa(date, LoaiCa.CASANG);
        if (caSang != null) {
            caSang.setSlDauBep(caSangDTO.getSlDauBep());
            caSang.setSlPhucVu(caSangDTO.getSlPhucVu());
            caSang.setSlLeTan(caSangDTO.getSlLeTan());
            caSang.setTgBatDau(caSangDTO.getTgBatDau());
            caSang.setTgKetThuc(caSangDTO.getTgKetThuc());

            caSangRepository.save(caSang);
            if (caSangDTO.getChiTietMon() != null) {
                List<ChiTietMon> chiTietMons = new ArrayList<>();
                for (ChiTietMon ct : caSangDTO.getChiTietMon()) {
                    Integer maMonAn = ct.getMaMonAn();
                    MonAn monAn = iMonAnService.getFoodById(maMonAn);

                    ChiTietMon chiTietMon = ChiTietMon.builder()
                            .ca(caSang)
                            .monAn(monAn)
                            .soLuong(ct.getSoLuong())
                            .build();

                    chiTietMons.add(chiTietMon);
                }

                chiTietMonRepository.saveAll(chiTietMons);
                caSang.setChiTietMon(chiTietMons);
            }
            return caSang;
        } else {
            throw new EntityNotFoundException("Morning shift not found in " + date);
        }

    }

    @Override
    public CaChieu updateAfternoonShift(LocalDate date, CaChieuDTO caChieuDTO) {
        CaChieu caChieu = caChieuRepository.findByNgayCongAndLoaiCa(date, LoaiCa.CACHIEU);
        if (caChieu != null) {
            caChieu.setSlDauBep(caChieuDTO.getSlDauBep());
            caChieu.setSlPhucVu(caChieuDTO.getSlPhucVu());
            caChieu.setSlLeTan(caChieuDTO.getSlLeTan());
            caChieu.setTgBatDau(caChieuDTO.getTgBatDau());
            caChieu.setTgKetThuc(caChieuDTO.getTgKetThuc());
            caChieu.setBuffet(caChieuDTO.getBuffet());
            caChieu.setPhuCapTrua(caChieuDTO.getPhuCapTrua());

            caChieuRepository.save(caChieu);
            if (caChieuDTO.getChiTietMon() != null) {
                List<ChiTietMon> chiTietMons = new ArrayList<>();
                for (ChiTietMon ct : caChieuDTO.getChiTietMon()) {
                    Integer maMonAn = ct.getMaMonAn();
                    MonAn monAn = iMonAnService.getFoodById(maMonAn);

                    ChiTietMon chiTietMon = ChiTietMon.builder()
                            .ca(caChieu)
                            .monAn(monAn)
                            .soLuong(ct.getSoLuong())
                            .build();

                    chiTietMons.add(chiTietMon);
                }

                chiTietMonRepository.saveAll(chiTietMons);
                caChieu.setChiTietMon(chiTietMons);
            }
            return caChieu;
        } else {
            throw new EntityNotFoundException("Afternoon shift not found in " + date);
        }
    }

    @Override
    public CaToi updateEveningShift(LocalDate date, CaToiDTO caToiDTO) {
        CaToi caToi = caToiRepository.findByNgayCongAndLoaiCa(date, LoaiCa.CATOI);
        if (caToi != null) {
            caToi.setSlDauBep(caToiDTO.getSlDauBep());
            caToi.setSlPhucVu(caToiDTO.getSlPhucVu());
            caToi.setSlLeTan(caToiDTO.getSlLeTan());
            caToi.setTgBatDau(caToiDTO.getTgBatDau());
            caToi.setTgKetThuc(caToiDTO.getTgKetThuc());

            caToiRepository.save(caToi);
            if (caToiDTO.getChiTietMon() != null) {
                List<ChiTietMon> chiTietMons = new ArrayList<>();
                for (ChiTietMon ct : caToiDTO.getChiTietMon()) {
                    Integer maMonAn = ct.getMaMonAn();
                    MonAn monAn = iMonAnService.getFoodById(maMonAn);

                    ChiTietMon chiTietMon = ChiTietMon.builder()
                            .ca(caToi)
                            .monAn(monAn)
                            .soLuong(ct.getSoLuong())
                            .build();

                    chiTietMons.add(chiTietMon);
                }

                chiTietMonRepository.saveAll(chiTietMons);
                caToi.setChiTietMon(chiTietMons);
            }
            return caToi;
        } else {
            throw new EntityNotFoundException("Evening shift not found in " + date);
        }
    }

}