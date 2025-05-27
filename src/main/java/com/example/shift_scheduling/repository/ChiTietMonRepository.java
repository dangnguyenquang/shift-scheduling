package com.example.shift_scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shift_scheduling.entity.ChiTietMon;
import com.example.shift_scheduling.entity.ChiTietMonId;

@Repository
public interface ChiTietMonRepository extends JpaRepository<ChiTietMon, ChiTietMonId>{
    
}
