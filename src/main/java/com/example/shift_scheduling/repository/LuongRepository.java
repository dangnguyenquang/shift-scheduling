package com.example.shift_scheduling.repository;

import com.example.shift_scheduling.entity.Feedback;
import com.example.shift_scheduling.entity.Luong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LuongRepository extends JpaRepository<Luong, Integer> {
    List<Luong> findAllByThangAndNam(int month, int year);
    Optional<Luong> findByMaNVAndThangAndNam(int employeeId, int month, int year);
}
