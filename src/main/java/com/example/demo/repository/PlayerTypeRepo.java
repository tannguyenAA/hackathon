package com.example.demo.repository;

import com.example.demo.entity.PlayerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTypeRepo extends JpaRepository<PlayerType, String> {
}
