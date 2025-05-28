package com.example.shift_scheduling.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shift_scheduling.dto.request.MonAnDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.entity.MonAn;
import com.example.shift_scheduling.service.IMonAnService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/food")
@RequiredArgsConstructor
public class MonAnController {
    private final IMonAnService iMonAnService;

    @GetMapping("/getAll")
    public ResponseData<?> getAllFood() {
        List<MonAn> monAn = this.iMonAnService.getAllFood();
        return new ResponseData<>(HttpStatus.OK.value(), "Get Food Succesfully", monAn);
    }

    @PostMapping("/addFood")
    public ResponseData<?> addFood(@RequestBody @Valid MonAnDTO request) {
        MonAn monAn = iMonAnService.saveFood(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create food successfully", monAn);
    }

    @GetMapping("/{id}")
    public ResponseData<?> findFoodById(@PathVariable Integer id) {
        MonAn monAn = iMonAnService.getFoodById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Food found", monAn);
    }

    @PutMapping("/updateFood/{id}")
    public ResponseData<?> updateFood(@PathVariable Integer id, @RequestBody @Valid MonAnDTO request) {
        MonAn monAn = iMonAnService.updateFood(id, request);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update food successfully", monAn);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteFood(@PathVariable Integer id) {
        iMonAnService.deleteFood(id);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Delete food successfully");
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseData<?> updateStatus(@PathVariable Integer id,
            @RequestParam boolean status) {
        iMonAnService.updateFoodStatus(id, status);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update status successfully");
    }

}
