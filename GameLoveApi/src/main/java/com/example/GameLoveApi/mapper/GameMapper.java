package com.example.GameLoveApi.mapper;

import com.example.GameLoveApi.dto.GameDTO;
import com.example.GameLoveApi.model.Game;

public class GameMapper
{
    public static GameDTO toDTO(Game game)
    {
        if (game == null) return null;
        return new GameDTO
        (
            game.getId(),
            game.getTitle()
        );
    }

    public static GameDTO toDTOWithLoveCount(Game game, int loveCount)
    {
        if (game == null) return null;
        return new GameDTO
        (
            game.getId(),
            game.getTitle(),
            loveCount
        );
    }

    public static Game toEntity(GameDTO dto)
    {
        if (dto == null) return null;
        Game game = new Game();
        game.setId(dto.getId());
        game.setTitle(dto.getTitle());
        return game;
    }
}