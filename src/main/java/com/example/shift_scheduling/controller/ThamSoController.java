package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.ThamSoDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.service.service_implementation.ThamSoServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thamso")
@Slf4j
public class ThamSoController {

    private final ThamSoServiceImpl thamSoServiceImpl;

    public ThamSoController(ThamSoServiceImpl thamSoServiceImpl) {
        this.thamSoServiceImpl = thamSoServiceImpl;
    }

    //    API lay ra tham so
    @GetMapping
    public ResponseData<ThamSoDTO> getThamSo() {
        ThamSoDTO dto = thamSoServiceImpl.getFirstThamSo();
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy tham số thành công", dto);
    }

    //    API cap nhat tham so
    @PutMapping
    public ResponseData<ThamSoDTO> updateThamSo(@Valid @RequestBody ThamSoDTO dto) {
        ThamSoDTO updated = thamSoServiceImpl.updateFirstThamSo(dto);
        return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật tham số thành công", updated);
    }
}
