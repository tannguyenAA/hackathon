package com.example.demo.repository;

import com.example.demo.entity.PlayerTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTimeTableRepo extends JpaRepository<PlayerTimeTable, String> {
}
