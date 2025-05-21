package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.DauBepDTO;
import com.example.shift_scheduling.dto.request.LeTanDTO;
import com.example.shift_scheduling.dto.request.PhucVuDTO;
import com.example.shift_scheduling.dto.request.UserRequestDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.service.service_implementation.NhanVienServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
}
