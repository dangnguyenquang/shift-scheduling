package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.NhanVienDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;
import com.example.shift_scheduling.entity.DauBep;
import com.example.shift_scheduling.entity.LeTan;
import com.example.shift_scheduling.entity.NhanVien;
import com.example.shift_scheduling.entity.PhucVu;
import com.example.shift_scheduling.repository.DauBepRepository;
import com.example.shift_scheduling.repository.LeTanRepository;
import com.example.shift_scheduling.repository.NhanVienRepository;
import com.example.shift_scheduling.repository.PhucVuRepository;
import com.example.shift_scheduling.service.INhanVienService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements INhanVienService {

    private final DauBepRepository dauBepRepository;
    private final PhucVuRepository phucVuRepository;
    private final LeTanRepository leTanRepository;
    private final NhanVienRepository nhanVienRepository;

    public NhanVienServiceImpl(DauBepRepository dauBepRepository,
                               PhucVuRepository phucVuRepository,
                               LeTanRepository leTanRepository,
                               NhanVienRepository nhanVienRepository) {
        this.dauBepRepository = dauBepRepository;
        this.phucVuRepository = phucVuRepository;
        this.leTanRepository = leTanRepository;
        this.nhanVienRepository = nhanVienRepository;
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
    public DauBepDTO getChefById(int id) {
        DauBep chef = dauBepRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đầu bếp với ID = " + id));

        return mapToDTOChef(chef);
    }

    @Override
    public List<DauBepDTO> getAllChefs() {
        return dauBepRepository.findAll().stream()
                .map(this::mapToDTOChef)
                .collect(Collectors.toList());
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
    public PhucVuDTO getWaiterById(int id) {
        PhucVu waiter = phucVuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find waiter by id = " + id));

        return mapToDTOWaiter(waiter);
    }

    @Override
    public List<PhucVuDTO> getAllWaiters() {
        return phucVuRepository.findAll().stream()
                .map(this::mapToDTOWaiter)
                .collect(Collectors.toList());
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

    @Override
    public LeTanDTO getReceptionistById(int id) {
        LeTan receptionist = leTanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find receptionist by id = " + id));

        return mapToDTOReceptionist(receptionist);
    }

    @Override
    public List<LeTanDTO> getAllReceptionists() {
        return leTanRepository.findAll().stream()
                .map(this::mapToDTOReceptionist)
                .collect(Collectors.toList());
    }

    @Override
    public NhanVienDTO getStaffById(int id) {
        NhanVien staff = nhanVienRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find staff by id = " + id));

        return mapToDTOStaff(staff);
    }

    @Override
    public List<NhanVienDTO> getAllStaffs() {
        return nhanVienRepository.findAll().stream()
                .map(this::mapToDTOStaff)
                .collect(Collectors.toList());
    }

    private DauBepDTO mapToDTOChef(DauBep chef) {
        return DauBepDTO.builder()
                .maNV(chef.getMaNV())
                .hoTen(chef.getHoTen())
                .soDienThoai(chef.getSoDienThoai())
                .diaChi(chef.getDiaChi())
                .gioiTinh(chef.getGioiTinh())
                .kinhNghiem(chef.getKinhNghiem())
                .dsLoaiMonAn(chef.getDsLoaiMonAn())
                .build();
    }

    private PhucVuDTO mapToDTOWaiter(PhucVu waiter) {
        return PhucVuDTO.builder()
                .maNV(waiter.getMaNV())
                .hoTen(waiter.getHoTen())
                .soDienThoai(waiter.getSoDienThoai())
                .diaChi(waiter.getDiaChi())
                .gioiTinh(waiter.getGioiTinh())
                .capBac(waiter.getCapDo())
                .build();
    }

    private LeTanDTO mapToDTOReceptionist(LeTan receptionist) {
        return LeTanDTO.builder()
                .maNV(receptionist.getMaNV())
                .hoTen(receptionist.getHoTen())
                .soDienThoai(receptionist.getSoDienThoai())
                .diaChi(receptionist.getDiaChi())
                .gioiTinh(receptionist.getGioiTinh())
                .ngoaiNgu(receptionist.isNgoaiNgu())
                .build();
    }

    private NhanVienDTO mapToDTOStaff(NhanVien staff) {
        return NhanVienDTO.builder()
                .maNV(staff.getMaNV())
                .hoTen(staff.getHoTen())
                .soDienThoai(staff.getSoDienThoai())
                .diaChi(staff.getDiaChi())
                .gioiTinh(staff.getGioiTinh())
                .build();
    }
}
