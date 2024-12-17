package com.example.demo.repository;

import com.example.demo.entity.PitchTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchTimeSlotRepo extends JpaRepository<PitchTimeSlot, String> {
}
