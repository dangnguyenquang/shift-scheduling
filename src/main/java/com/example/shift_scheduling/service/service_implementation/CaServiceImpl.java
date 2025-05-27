package com.example.shift_scheduling.service.service_implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shift_scheduling.dto.request.CaChieuDTO;
import com.example.shift_scheduling.dto.request.CaSangDTO;
import com.example.shift_scheduling.dto.request.CaThongKeDTO;
import com.example.shift_scheduling.dto.request.CaToiDTO;
import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.CaChieu;
import com.example.shift_scheduling.entity.CaSang;
import com.example.shift_scheduling.entity.CaToi;
import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.entity.ChiTietMonId;
import com.example.shift_scheduling.entity.MonAn;
import com.example.shift_scheduling.entity.NhanVien;
import com.example.shift_scheduling.repository.CaChieuRepository;
import com.example.shift_scheduling.repository.CaRepository;
import com.example.shift_scheduling.repository.CaSangRepository;
import com.example.shift_scheduling.repository.CaToiRepository;
import com.example.shift_scheduling.repository.ChiTietCaRepository;
import com.example.shift_scheduling.repository.ChiTietMonRepository;
import com.example.shift_scheduling.service.ICaService;
import com.example.shift_scheduling.service.IMonAnService;
import com.example.shift_scheduling.util.LoaiCa;
import com.example.shift_scheduling.util.TrangThaiXepCa;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
    private final ChiTietCaRepository chiTietCaRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    @Transactional
    public void autoGenerateShift(LocalDate date, Integer dayNums) {
        LocalDate maxDate = caRepository.findMaxDate();
        if (maxDate != null) {
            if (date.isBefore(maxDate) || date.isAfter(maxDate.plusDays(1))) {
                throw new RuntimeException("Ngay bat dau phai la: " + maxDate.plusDays(1));
            }
        }
        else {
            LocalDate today = LocalDate.now();
            if (!date.isEqual(today)) {
                throw new RuntimeException("Ngay bat dau phai la: " + today);
            }
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
        entityManager.flush();
        entityManager.clear();
        if (ca.getChiTietMon() != null) {
            List<ChiTietMon> chiTietMons = new ArrayList<>();
            for (ChiTietMon ct : ca.getChiTietMon()) {
                Integer maMonAn = ct.getMaMonAn();
                MonAn monAn = iMonAnService.getFoodById(maMonAn);

                ChiTietMonId chiTietMonId = new ChiTietMonId();
                chiTietMonId.setFoodId(maMonAn);
                chiTietMonId.setShiftId(saveShift.getId());

                ChiTietMon chiTietMon = ChiTietMon.builder()
                        .ca(saveShift)
                        .monAn(monAn)
                        .soLuong(ct.getSoLuong())
                        .id(chiTietMonId)
                        .build();
                log.info(">>>>>>>>>>>>>>>>>: " + chiTietMon);
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
                    ChiTietMonId chiTietMonId = new ChiTietMonId();
                    chiTietMonId.setFoodId(maMonAn);
                    chiTietMonId.setShiftId(caSang.getId());
                    if (chiTietMonRepository.findById(chiTietMonId).isPresent()) {
                        MonAn monAn = iMonAnService.getFoodById(maMonAn);

                        ChiTietMon chiTietMon = ChiTietMon.builder()
                                .ca(caSang)
                                .monAn(monAn)
                                .soLuong(ct.getSoLuong())
                                .id(chiTietMonId)
                                .build();

                        chiTietMons.add(chiTietMon);
                    } else {
                        throw new EntityNotFoundException("FoodId not found in DetailedShiftFood");
                    }

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
                    ChiTietMonId chiTietMonId = new ChiTietMonId();
                    chiTietMonId.setFoodId(maMonAn);
                    chiTietMonId.setShiftId(caChieu.getId());
                    if (chiTietMonRepository.findById(chiTietMonId) != null) {
                        MonAn monAn = iMonAnService.getFoodById(maMonAn);

                        ChiTietMon chiTietMon = ChiTietMon.builder()
                                .ca(caChieu)
                                .monAn(monAn)
                                .soLuong(ct.getSoLuong())
                                .id(chiTietMonId)
                                .build();

                        chiTietMons.add(chiTietMon);
                    } else {
                        throw new EntityNotFoundException("FoodId not found in DetailedShiftFood");
                    }

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
                    ChiTietMonId chiTietMonId = new ChiTietMonId();
                    chiTietMonId.setFoodId(maMonAn);
                    chiTietMonId.setShiftId(caToi.getId());
                    if (chiTietMonRepository.findById(chiTietMonId) != null) {
                        MonAn monAn = iMonAnService.getFoodById(maMonAn);

                        ChiTietMon chiTietMon = ChiTietMon.builder()
                                .ca(caToi)
                                .monAn(monAn)
                                .soLuong(ct.getSoLuong())
                                .id(chiTietMonId)
                                .build();

                        chiTietMons.add(chiTietMon);
                    } else {
                        throw new EntityNotFoundException("FoodId not found in DetailedShiftFood");
                    }

                }

                chiTietMonRepository.saveAll(chiTietMons);
                caToi.setChiTietMon(chiTietMons);
            }
            return caToi;
        } else {
            throw new EntityNotFoundException("Evening shift not found in " + date);
        }
    }

    public List<CaThongKeDTO> thongKeCa(int thang, int nam) {
        LocalDate startDate = LocalDate.of(nam, thang, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<ChiTietCa> danhSachChiTietCa = chiTietCaRepository.findByCa_NgayCongBetweenAndTtXepCa(startDate, endDate, TrangThaiXepCa.THANHCONG);

        Map<Integer, CaThongKeDTO> thongKeTheoNhanVien = new HashMap<>();

        for (ChiTietCa chiTietCa : danhSachChiTietCa) {
            NhanVien nv = chiTietCa.getNhanVien();
            Ca ca = chiTietCa.getCa();

            if (nv == null || ca == null) continue;

            int maNV = nv.getMaNV();

            thongKeTheoNhanVien.putIfAbsent(maNV, CaThongKeDTO.builder()
                    .employeeId(maNV)
                    .caSang(0)
                    .caChieu(0)
                    .caToi(0)
                    .soBuoiBuffet(0)
                    .soBuoiSuKien(0)
                    .build());

            CaThongKeDTO dto = thongKeTheoNhanVien.get(maNV);

            if (ca instanceof CaSang) {
                dto.setCaSang(dto.getCaSang() + 1);
            } else if (ca instanceof CaChieu chieu) {
                dto.setCaChieu(dto.getCaChieu() + 1);
                if (chieu.getBuffet() != null && !chieu.getBuffet().isBlank()) {
                    dto.setSoBuoiBuffet(dto.getSoBuoiBuffet() + 1);
                }
            } else if (ca instanceof CaToi toi) {
                dto.setCaToi(dto.getCaToi() + 1);
                if (toi.getSuKien() != null && !toi.getSuKien().isBlank()) {
                    dto.setSoBuoiSuKien(dto.getSoBuoiSuKien() + 1);
                }
            }
        }

        return new ArrayList<>(thongKeTheoNhanVien.values());
    }
}