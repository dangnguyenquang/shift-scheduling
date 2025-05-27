package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.service.service_implementation.LuongServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
@Validated
@Slf4j
public class LuongController {

    private final LuongServiceImpl luongServiceImpl;

    public LuongController(LuongServiceImpl luongServiceImpl) {
        this.luongServiceImpl = luongServiceImpl;
    }

    @GetMapping("/calculate")
    public ResponseData<?> calculateSalary(@RequestParam int month, @RequestParam int year) {
        luongServiceImpl.calculateSalary(month, year);
        return new ResponseData<>(HttpStatus.OK.value(), "Salary calculation completed", null);
    }

    @GetMapping("/all")
    public ResponseData<?> getAllSalaries(@RequestParam int month, @RequestParam int year) {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched all salaries", luongServiceImpl.getAllSalaries(month, year));
    }

    @GetMapping("/{employeeId}")
    public ResponseData<?> getSalaryByEmployee(
            @PathVariable int employeeId,
            @RequestParam int month,
            @RequestParam int year) {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched employee salary", luongServiceImpl.getSalaryByEmployee(employeeId, month, year));
    }
}
