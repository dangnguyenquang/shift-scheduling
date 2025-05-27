package com.example.shift_scheduling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shift_scheduling.dto.request.MonAnDTO;
import com.example.shift_scheduling.entity.MonAn;

@Service
public interface IMonAnService {
    
    MonAn saveFood(MonAnDTO request);
    List<MonAn> getAllFood();
    MonAn getFoodById(Integer id);
    MonAn updateFood(Integer id, MonAnDTO request);
    public void deleteFood(Integer id);
    public void updateFoodStatus(Integer id, boolean status);

    
}
