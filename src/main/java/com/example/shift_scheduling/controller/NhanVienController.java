package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.*;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.service.service_implementation.NhanVienServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
@Slf4j
public class NhanVienController {

    private final NhanVienServiceImpl nhanVienServiceIml;

    public NhanVienController(NhanVienServiceImpl nhanVienServiceIml) {
        this.nhanVienServiceIml = nhanVienServiceIml;
    }

    //    API them dau bep moi
    @PostMapping("/chef")
    public ResponseData<Integer> addChef(@Valid @RequestBody DauBepDTO daubep) {
        int result = nhanVienServiceIml.addChef(daubep);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Chef added successfully", result);
    }

    //    API them moi phuc vu
    @PostMapping("/waiter")
    public ResponseData<Integer> addWaiter(@Valid @RequestBody PhucVuDTO phucvu) {
        int result = nhanVienServiceIml.addWaiter(phucvu);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Waiter added successfully", result);
    }

    //    API them le tan moi
    @PostMapping("/receptionist")
    public ResponseData<Integer> addReceptionist(@Valid @RequestBody LeTanDTO letan) {
        log.info("le tan: " + letan.toString());
        int result = nhanVienServiceIml.addReceptionist(letan);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Receptionist added successfully", result);
    }

    //    API lay tat ca dau bep
    @GetMapping("/chef")
    public ResponseData<?> getAllChefs() {
        List<DauBepDTO> list = nhanVienServiceIml.getAllChefs();
        return new ResponseData<>(HttpStatus.OK.value(), "Get chef list successfully", list);
    }

    //    API lay dau bep dua tren id nhan vien
    @GetMapping("/chef/{id}")
    public ResponseData<?> getChefById(@PathVariable Integer id) {
        DauBepDTO dto = nhanVienServiceIml.getChefById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get chef successfully", dto);
    }

    //    API lay tat ca phuc vu
    @GetMapping("/waiter")
    public ResponseData<?> getAllWaiters() {
        List<PhucVuDTO> list = nhanVienServiceIml.getAllWaiters();
        return new ResponseData<>(HttpStatus.OK.value(), "Get waiter list successfully", list);
    }

    //    API lay phuc vu dua tren id nhan vien
    @GetMapping("/waiter/{id}")
    public ResponseData<?> getWaiterById(@PathVariable Integer id) {
        PhucVuDTO dto = nhanVienServiceIml.getWaiterById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get waiter successfully", dto);
    }

    //    API lay tat ca le tan
    @GetMapping("/receptionist")
    public ResponseData<?> getAllReceptionists() {
        List<LeTanDTO> list = nhanVienServiceIml.getAllReceptionists();
        return new ResponseData<>(HttpStatus.OK.value(), "Get receptionist list successfully", list);
    }

    //    API lay le tan dua tren id nhan vien
    @GetMapping("/receptionist/{id}")
    public ResponseData<?> getReceptionistById(@PathVariable Integer id) {
        LeTanDTO dto = nhanVienServiceIml.getReceptionistById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get receptionist successfully", dto);
    }

    //    API lay tat ca nhan vien
    @GetMapping("/")
    public ResponseData<?> getAllStaffs() {
        List<NhanVienDTO> list = nhanVienServiceIml.getAllStaffs();
        return new ResponseData<>(HttpStatus.OK.value(), "Get staff list successfully", list);
    }

    //    API lay ra nhan vien dua tren id nhan vien
    @GetMapping("/{id}")
    public ResponseData<?> getStaffById(@PathVariable Integer id) {
        NhanVienDTO dto = nhanVienServiceIml.getStaffById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get staff successfully", dto);
    }

    //    API dang ky ca lam
    @PostMapping("/register-shift")
    public ResponseData<?> shiftRegister(@RequestParam Integer maNv, @RequestParam Integer maCa) {
        ChiTietCaDTO chiTietCaDTO = nhanVienServiceIml.shiftRegister(maNv, maCa);
        return new ResponseData<>(HttpStatus.OK.value(), "Register successfully", chiTietCaDTO);
    }
}
