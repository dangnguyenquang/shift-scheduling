package com.example.shift_scheduling.repository;

import com.example.shift_scheduling.entity.LeTan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeTanRepository extends JpaRepository<LeTan, Integer> {
}
