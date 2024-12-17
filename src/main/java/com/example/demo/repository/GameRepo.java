package com.example.demo.repository;

import com.example.demo.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, String> {
    @Query(value = "select g.* from gameregistereduser gr join bluc.game g on g.id = gr.gameId where gr.userId = :userId", nativeQuery = true)
    List<Game> findMyGame(String userId);
}
