package com.example.GameLoveApi.controller;

import com.example.GameLoveApi.model.Game;
import com.example.GameLoveApi.model.GameLove;
import com.example.GameLoveApi.model.Player;
import com.example.GameLoveApi.service.GameLoveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameLoveController
{
    @Autowired
    private GameLoveService gameLoveService;

    // Love Endpoint
    @PostMapping("/{gameId}/love")
    public ResponseEntity<GameLove> loveGame(@PathVariable Long gameId, @RequestBody Player player)
    {
        GameLove gameLove = gameLoveService.loveGame(player, gameId);
        return ResponseEntity.ok(gameLove);
    }

    // Unlove Endpoint
    @DeleteMapping("/{gameId}/love")
    public ResponseEntity<Void> unloveGame(@PathVariable Long gameId, @RequestBody Player player)
    {
        gameLoveService.unloveGame(player, gameId);
        return ResponseEntity.ok().build();
    }

    // Get Loved Games Endpoint
    @GetMapping("/player/{playerId}/loved")
    public ResponseEntity<List<Game>> getLovedGames(@PathVariable Long playerId)
    {
        List<Game> lovedGames = gameLoveService.getLovedGames(playerId);
        return ResponseEntity.ok(lovedGames);
    }

    // Get Most Loved Games Endpoint
    @GetMapping("/most-loved")
    public ResponseEntity<List<Map<String, Object>>> getMostLovedGames(@RequestParam(defaultValue = "10") int limit)
    {
        List<Object[]> results = gameLoveService.getMostLovedGames(limit);
        List<Map<String, Object>> response = results.stream()
                .map(result -> Map.of
                (
                "gameId", ((Game) result[0]).getId(),
                "loveCount", result[1]
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get All Games Endpoint
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames()
    {
        List<Game> games = gameLoveService.getAllGames();
        return ResponseEntity.ok(games);
    }
}