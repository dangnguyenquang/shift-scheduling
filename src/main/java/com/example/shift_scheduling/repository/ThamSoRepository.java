package com.example.shift_scheduling.repository;

import com.example.shift_scheduling.entity.Feedback;
import com.example.shift_scheduling.entity.ThamSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThamSoRepository extends JpaRepository<ThamSo, Integer> {
}
