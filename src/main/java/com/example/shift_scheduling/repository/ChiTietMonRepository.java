package com.example.shift_scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shift_scheduling.entity.ChiTietMon;

@Repository
public interface ChiTietMonRepository extends JpaRepository<ChiTietMon, Integer>{
    
}
