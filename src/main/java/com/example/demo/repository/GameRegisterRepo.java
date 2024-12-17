package com.example.demo.repository;

import com.example.demo.entity.GameRegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRegisterRepo extends JpaRepository<GameRegisteredUser, String> {
    Optional<GameRegisteredUser> findByUserIdAndGameId(String userId, String gameId);
}
