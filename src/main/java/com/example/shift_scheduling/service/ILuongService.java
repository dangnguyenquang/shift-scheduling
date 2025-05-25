package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.LuongDTO;

import java.util.List;

public interface ILuongService {
    void calculateSalary(int month, int year);

    List<LuongDTO> getAllSalaries(int month, int year);

    LuongDTO getSalaryByEmployee(int employeeId, int month, int year);
}
