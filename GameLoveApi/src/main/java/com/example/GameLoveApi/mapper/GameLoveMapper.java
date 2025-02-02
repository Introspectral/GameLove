package com.example.GameLoveApi.mapper;

import com.example.GameLoveApi.dto.GameLoveDTO;
import com.example.GameLoveApi.model.GameLove;

public class GameLoveMapper
{
    public static GameLoveDTO toDTO(GameLove gameLove)
    {
        if (gameLove == null) return null;
        return new GameLoveDTO
        (
            gameLove.getId(),
            PlayerMapper.toDTO(gameLove.getPlayer()),
            GameMapper.toDTO(gameLove.getGame()),
            gameLove.getCreatedAt()
        );
    }
}
