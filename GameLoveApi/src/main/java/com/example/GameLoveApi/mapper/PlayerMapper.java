package com.example.GameLoveApi.mapper;

import com.example.GameLoveApi.dto.PlayerDTO;
import com.example.GameLoveApi.model.Player;

public class PlayerMapper
{
    public static PlayerDTO toDTO(Player player)
    {
        if (player == null) return null;
        return new PlayerDTO
        (
            player.getId(),
            player.getUsername()
        );
    }

    public static Player toEntity(PlayerDTO dto)
    {
        if (dto == null) return null;
        Player player = new Player();
        player.setId(dto.getId());
        player.setUsername(dto.getUsername());
        return player;
    }
}
