package com.example.demo.repository;

import com.example.demo.entity.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PitchRepo extends JpaRepository<Pitch, String> {
    List<Pitch> findByUserIdAndFieldId(String userId, String fieldId);
    List<Pitch> findByFieldId(String fieldId);
}
