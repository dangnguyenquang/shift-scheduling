package com.example.shift_scheduling.service.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.CaToi;
import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.entity.DauBep;
import com.example.shift_scheduling.entity.LeTan;
import com.example.shift_scheduling.entity.MonAn;
import com.example.shift_scheduling.entity.NhanVien;
import com.example.shift_scheduling.entity.PhucVu;
import com.example.shift_scheduling.repository.ChiTietCaRepository;
import com.example.shift_scheduling.service.IMonAnService;
import com.example.shift_scheduling.service.IXepCaService;
import com.example.shift_scheduling.util.LoaiMon;
import com.example.shift_scheduling.util.TrangThaiXepCa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class XepCaServiceimpl implements IXepCaService {

    private final ChiTietCaRepository chiTietCaRepository;
    private final IMonAnService iMonAnService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void autoScheduleShift() {
        List<ChiTietCa> chuaXep = chiTietCaRepository.findByTtXepCa(TrangThaiXepCa.CHUAXEP);

        // Lọc chi tiết ca đăng ký theo ca
        Map<Ca, List<ChiTietCa>> caDangKy = chuaXep.stream()
                .collect(Collectors.groupingBy(ChiTietCa::getCa));

        for (Map.Entry<Ca, List<ChiTietCa>> entry : caDangKy.entrySet()) {
            Ca ca = entry.getKey();
            List<ChiTietCa> danhSachDangKy = entry.getValue();

            handleScheduleShift(ca, danhSachDangKy);
        }

         chiTietCaRepository.saveAll(chuaXep);
    }

    @Override
    public void handleScheduleShift(Ca ca, List<ChiTietCa> dsDangKy) {
        // Lọc chi tiết ca đăng kí theo Nhân Viên
        List<ChiTietCa> dsDauBep = dsDangKy.stream()
                .filter(e -> e.getNhanVien() instanceof DauBep)
                .collect(Collectors.toList());

        List<ChiTietCa> dsPhucVu = dsDangKy.stream()
                .filter(e -> e.getNhanVien() instanceof PhucVu)
                .collect(Collectors.toList());

        List<ChiTietCa> dsLeTan = dsDangKy.stream()
                .filter(e -> e.getNhanVien() instanceof LeTan)
                .collect(Collectors.toList());

        scheduleStaff(ca, dsDauBep, ca.getSlDauBep());
        scheduleStaff(ca, dsPhucVu, ca.getSlPhucVu());
        scheduleStaff(ca, dsLeTan, ca.getSlLeTan());

    }

    @Override
    public void scheduleStaff(Ca ca, List<ChiTietCa> dsDangKy, Integer soLuong) {
        // Chọn danh sách ưu tiên theo yêu cầu
        List<ChiTietCa> dsUuTien = dsDangKy.stream()
                .filter(c -> {
                    NhanVien nv = c.getNhanVien();

                    if (nv instanceof DauBep dauBep) {
                        List<ChiTietMon> chiTietMons = ca.getChiTietMon();
                        Set<LoaiMon> cacLoaiMonTrongCa = chiTietMons.stream()
                                .map(ct -> {
                                    MonAn monAnById = iMonAnService.getFoodById(ct.getMonAn().getMaMonAn());
                                    return monAnById.getLoaiMon();
                                })
                                .collect(Collectors.toSet());

                        boolean phuHop = dauBep.getDsLoaiMonAn().stream()
                                .anyMatch(cacLoaiMonTrongCa::contains);

                        return phuHop;
                    }
                    if (nv instanceof LeTan leTan) {
                        if (ca instanceof CaToi) {
                            return leTan.isNgoaiNgu();
                        }
                        return true;
                    }

                    return true;
                })
                .collect(Collectors.toList());

        List<ChiTietCa> dsKhongUuTien = dsDangKy.stream()
                .filter(c -> !dsUuTien.contains(c))
                .collect(Collectors.toList());

        List<ChiTietCa> dsNhanVien;

        if (dsUuTien.size() >= soLuong) {
            dsNhanVien = dsUuTien;
        } else {
            dsNhanVien = new ArrayList<>();
            dsNhanVien.addAll(dsUuTien);
            dsNhanVien.addAll(dsKhongUuTien);
        }
        
        //Set trạng thái là THANHCONG
        int daChon = 0;
        for (ChiTietCa ctc : dsNhanVien) {
            if (daChon >= soLuong)
                break;
                ctc.setTtXepCa(TrangThaiXepCa.THANHCONG);
                ctc.getNhanVien().getDsCaLamViec().add(ctc);
                daChon++;
        }

        dsDangKy.stream()
                .filter(ctc -> ctc.getTtXepCa().equals(TrangThaiXepCa.CHUAXEP))
                .forEach(ctc -> ctc.setTtXepCa(TrangThaiXepCa.THATBAI));
    }

}
