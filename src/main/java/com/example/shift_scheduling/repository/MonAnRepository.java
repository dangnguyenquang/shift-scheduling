package com.example.shift_scheduling.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shift_scheduling.entity.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Integer> {

}
