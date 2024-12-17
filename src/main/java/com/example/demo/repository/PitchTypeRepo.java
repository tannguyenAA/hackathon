package com.example.demo.repository;

import com.example.demo.entity.PitchType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchTypeRepo extends JpaRepository<PitchType, String> {
}
