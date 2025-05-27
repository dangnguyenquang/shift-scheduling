package com.example.shift_scheduling.repository;

import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.util.TrangThaiXepCa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChiTietCaRepository extends JpaRepository<ChiTietCa, Integer> {
    List<ChiTietCa> findByCa_NgayCongBetweenAndTtXepCa(LocalDate start, LocalDate end, TrangThaiXepCa tt);
}
