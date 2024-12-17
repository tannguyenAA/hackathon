package com.example.demo.controller;

import com.example.demo.controller.request.GameCreateCommand;
import com.example.demo.controller.request.SearchGameCommand;
import com.example.demo.entity.Game;
import com.example.demo.service.FieldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")
public class GameController {
    public final FieldService fieldService;

    public GameController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/search")
    public List<Game> getGames(@ModelAttribute SearchGameCommand command, @RequestHeader("UserId") String userId) {
        return fieldService.getGames(userId, command);
    }

    @PostMapping()
    public Game createGame(@RequestBody GameCreateCommand command, @RequestHeader("UserId") String userId) {
        return fieldService.createGame(userId, command);
    }

    @GetMapping("/match")
    public List<Game> getMatches(@RequestHeader("UserId") String userId) {
        return fieldService.matchGame(userId);
    }

    @GetMapping("/myGame")
    public List<Game> getMyGame(@RequestHeader("UserId") String userId) {
        return fieldService.myGames(userId);
    }

    @PostMapping("/{gameId}/joinGame")
    public Boolean joinGame(@PathVariable String gameId, @RequestHeader("UserId") String userId) {
        return fieldService.joinGame(userId,gameId);
    }
}
