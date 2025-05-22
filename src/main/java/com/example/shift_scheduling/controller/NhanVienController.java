package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.NhanVienDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;
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

    @PostMapping("/chef")
    public ResponseData<Integer> addChef(@Valid @RequestBody DauBepDTO daubep) {
        int result = nhanVienServiceIml.addChef(daubep);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Chef added successfully", result);
    }

    @PostMapping("/waiter")
    public ResponseData<Integer> addWaiter(@Valid @RequestBody PhucVuDTO phucvu) {
        int result = nhanVienServiceIml.addWaiter(phucvu);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Waiter added successfully", result);
    }

    @PostMapping("/receptionist")
    public ResponseData<Integer> addReceptionist(@Valid @RequestBody LeTanDTO letan) {
        int result = nhanVienServiceIml.addReceptionist(letan);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Receptionist added successfully", result);
    }

    @GetMapping("/chef")
    public ResponseData<?> getAllChefs() {
        List<DauBepDTO> list = nhanVienServiceIml.getAllChefs();
        return new ResponseData<>(HttpStatus.OK.value(), "Get chef list successfully", list);
    }

    @GetMapping("/chef/{id}")
    public ResponseData<?> getChefById(@PathVariable Integer id) {
        DauBepDTO dto = nhanVienServiceIml.getChefById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get chef successfully", dto);
    }

    @GetMapping("/waiter")
    public ResponseData<?> getAllWaiters() {
        List<PhucVuDTO> list = nhanVienServiceIml.getAllWaiters();
        return new ResponseData<>(HttpStatus.OK.value(), "Get waiter list successfully", list);
    }

    @GetMapping("/waiter/{id}")
    public ResponseData<?> getWaiterById(@PathVariable Integer id) {
        PhucVuDTO dto = nhanVienServiceIml.getWaiterById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get waiter successfully", dto);
    }

    @GetMapping("/receptionist")
    public ResponseData<?> getAllReceptionists() {
        List<LeTanDTO> list = nhanVienServiceIml.getAllReceptionists();
        return new ResponseData<>(HttpStatus.OK.value(), "Get receptionist list successfully", list);
    }

    @GetMapping("/receptionist/{id}")
    public ResponseData<?> getReceptionistById(@PathVariable Integer id) {
        LeTanDTO dto = nhanVienServiceIml.getReceptionistById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get receptionist successfully", dto);
    }

    @GetMapping("/")
    public ResponseData<?> getAllStaffs() {
        List<NhanVienDTO> list = nhanVienServiceIml.getAllStaffs();
        return new ResponseData<>(HttpStatus.OK.value(), "Get staff list successfully", list);
    }

    @GetMapping("/{id}")
    public ResponseData<?> getStaffById(@PathVariable Integer id) {
        NhanVienDTO dto = nhanVienServiceIml.getStaffById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get staff successfully", dto);
    }
}
