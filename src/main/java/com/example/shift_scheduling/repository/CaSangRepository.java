package com.example.shift_scheduling.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shift_scheduling.entity.CaSang;
import com.example.shift_scheduling.util.LoaiCa;

@Repository
public interface CaSangRepository extends JpaRepository<CaSang, Integer> {
        CaSang findByNgayCongAndLoaiCa(LocalDate date, LoaiCa loaiCa);

}
