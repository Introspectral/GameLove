package com.example.GameLoveApi.dto;

import java.time.LocalDateTime;

public class GameLoveDTO
{
    private Long id;
    private PlayerDTO player;
    private GameDTO game;
    private LocalDateTime createdAt;

    public GameLoveDTO(Long id, PlayerDTO player, GameDTO game, LocalDateTime createdAt)
    {
        this.id = id;
        this.player = player;
        this.game = game;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PlayerDTO getPlayer() { return player; }
    public void setPlayer(PlayerDTO player) { this.player = player; }
    public GameDTO getGame() { return game; }
    public void setGame(GameDTO game) { this.game = game; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}