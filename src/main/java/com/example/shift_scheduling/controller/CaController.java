package com.example.shift_scheduling.controller;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shift_scheduling.dto.request.CaChieuDTO;
import com.example.shift_scheduling.dto.request.CaSangDTO;
import com.example.shift_scheduling.dto.request.CaToiDTO;
import com.example.shift_scheduling.dto.request.ChiTietCaDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.entity.CaChieu;
import com.example.shift_scheduling.entity.CaSang;
import com.example.shift_scheduling.entity.CaToi;
import com.example.shift_scheduling.service.ICaService;
import com.example.shift_scheduling.service.IChiTietCaService;
import com.example.shift_scheduling.service.IXepCaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shift")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CaController {
    private final ICaService iCaService;
    private final IXepCaService iXepCaService;
    private final IChiTietCaService iChiTietCaService;

    @PostMapping("/auto-generate")
    public ResponseData<?> autoGenerateShifts(@RequestParam @Valid LocalDate date, @RequestParam Integer days) {
        iCaService.autoGenerateShift(date, days);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Auto generate successfully!");
    }

    @GetMapping("/getAll")
    public ResponseData<?> getAllShifts() {
        List<Ca> ds = iCaService.getAllShifts();
        return new ResponseData<>(HttpStatus.OK.value(), "Get shifts successfully!", ds);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteShift(@PathVariable Integer id) {
        iCaService.deleteShift(id);
        ;
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete shifts successfully!");
    }

    @PutMapping("/update/morning")
    public ResponseData<?> updateMorningShift(@RequestParam LocalDate date, @RequestBody CaSangDTO caSangDTO) {
        CaSang caSang = iCaService.updateMorningShift(date, caSangDTO);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update morning shift successfully!", caSang);
    }

    @PutMapping("/update/afternoon")
    public ResponseData<?> updateAfternoonShift(@RequestParam LocalDate date, @RequestBody CaChieuDTO caChieuDTO) {
        CaChieu caChieu = iCaService.updateAfternoonShift(date, caChieuDTO);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update afternoon shift successfully!", caChieu);
    }

    @PutMapping("/update/evening")
    public ResponseData<?> updatEeveningShift(@RequestParam LocalDate date, @RequestBody CaToiDTO caToiDTO) {
        CaToi caToi = iCaService.updateEveningShift(date, caToiDTO);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update evening shift successfully!", caToi);
    }

    @PostMapping("/schedule")
    public ResponseData<?> shiftSchedule() {
        iXepCaService.autoScheduleShift();
        return new ResponseData<>(HttpStatus.CREATED.value(), "Auto schedule shifts successfully");
    }

    @GetMapping("/getAll-detailedShifts")
    public ResponseData<?> getAllDetaiedShifts() {
        List<ChiTietCaDTO> ds = iChiTietCaService.getAllDetailedShifts();
        return new ResponseData<>(HttpStatus.OK.value(), "Get shifts successfully!", ds);
    }

}
