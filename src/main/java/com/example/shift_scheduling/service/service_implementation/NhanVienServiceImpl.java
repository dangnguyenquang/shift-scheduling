package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;
import com.example.shift_scheduling.entity.DauBep;
import com.example.shift_scheduling.entity.LeTan;
import com.example.shift_scheduling.entity.PhucVu;
import com.example.shift_scheduling.repository.DauBepRepository;
import com.example.shift_scheduling.repository.LeTanRepository;
import com.example.shift_scheduling.repository.PhucVuRepository;
import com.example.shift_scheduling.service.INhanVienService;
import org.springframework.stereotype.Service;

@Service
public class NhanVienServiceImpl implements INhanVienService {

    private final DauBepRepository dauBepRepository;
    private final PhucVuRepository phucVuRepository;
    private final LeTanRepository leTanRepository;

    public NhanVienServiceImpl(DauBepRepository dauBepRepository,
                               PhucVuRepository phucVuRepository,
                               LeTanRepository leTanRepository) {
        this.dauBepRepository = dauBepRepository;
        this.phucVuRepository = phucVuRepository;
        this.leTanRepository = leTanRepository;
    }

    @Override
    public int addChef(DauBepDTO dto) {
        DauBep db = new DauBep(
                dto.getHoTen(),
                dto.getSoDienThoai(),
                dto.getDiaChi(),
                dto.getGioiTinh(),
                dto.getKinhNghiem(),
                dto.getDsLoaiMonAn()
        );

        dauBepRepository.save(db);
        return 1;
    }

    @Override
    public int addWaiter(PhucVuDTO dto) {
        PhucVu pv = new PhucVu(
                dto.getHoTen(),
                dto.getSoDienThoai(),
                dto.getDiaChi(),
                dto.getGioiTinh(),
                dto.getCapBac()
        );

        phucVuRepository.save(pv);
        return 1;
    }

    @Override
    public int addReceptionist(LeTanDTO dto) {
        LeTan lt = new LeTan(
                dto.getHoTen(),
                dto.getSoDienThoai(),
                dto.getDiaChi(),
                dto.getGioiTinh(),
                dto.isNgoaiNgu()
        );

        leTanRepository.save(lt);
        return 1;
    }
}
