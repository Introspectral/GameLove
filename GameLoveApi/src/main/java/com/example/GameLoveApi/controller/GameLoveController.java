package com.example.GameLoveApi.controller;

import com.example.GameLoveApi.dto.GameDTO;
import com.example.GameLoveApi.dto.GameLoveDTO;
import com.example.GameLoveApi.dto.PlayerDTO;
import com.example.GameLoveApi.service.GameLoveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameLoveController
{

    private final GameLoveService gameLoveService;

    public GameLoveController(GameLoveService gameLoveService)
    {
        this.gameLoveService = gameLoveService;
    }

    @PostMapping("/{gameId}/love")
    public ResponseEntity<GameLoveDTO> loveGame(@PathVariable Long gameId, @RequestBody PlayerDTO player)
    {
        return ResponseEntity.ok(gameLoveService.loveGame(player, gameId));
    }

    @GetMapping("/player/{playerId}/loved")
    public ResponseEntity<List<GameDTO>> getLovedGames(@PathVariable Long playerId)
    {
        return ResponseEntity.ok(gameLoveService.getLovedGames(playerId));
    }

    @GetMapping("/most-loved")
    public ResponseEntity<List<GameDTO>> getMostLovedGames(@RequestParam(defaultValue = "10") int limit)
    {
        return ResponseEntity.ok(gameLoveService.getMostLovedGames(limit));
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames()
    {
        return ResponseEntity.ok(gameLoveService.getAllGames());
    }
}