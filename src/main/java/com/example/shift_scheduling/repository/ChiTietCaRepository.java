package com.example.shift_scheduling.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shift_scheduling.entity.ChiTietCa;
import com.example.shift_scheduling.entity.ChiTietCaId;
import com.example.shift_scheduling.util.TrangThaiXepCa;


@Repository
public interface ChiTietCaRepository extends JpaRepository<ChiTietCa, ChiTietCaId> {
    List<ChiTietCa> findByCa_NgayCongBetweenAndTtXepCa(LocalDate start, LocalDate end, TrangThaiXepCa tt);
}
