package com.example.shift_scheduling.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shift_scheduling.entity.Ca;
import com.example.shift_scheduling.util.LoaiCa;

@Repository
public interface CaRepository extends JpaRepository<Ca, Integer> {
    @Query("Select MAX(c.ngayCong) from Shift c")
    public LocalDate findMaxDate();

    boolean existsByNgayCongAndLoaiCa(LocalDate date, LoaiCa type);

    Ca findByNgayCongAndLoaiCa(LocalDate date, LoaiCa loaiCa);
}
