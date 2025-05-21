package com.example.shift_scheduling.repository;

import com.example.shift_scheduling.entity.DauBep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DauBepRepository extends JpaRepository<DauBep, Integer> {
}
