package com.example.GameLoveApi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.GameLoveApi.dto.GameLoveDTO;
import com.example.GameLoveApi.dto.PlayerDTO;
import com.example.GameLoveApi.mapper.GameLoveMapper;
import com.example.GameLoveApi.mapper.PlayerMapper;
import com.example.GameLoveApi.model.Game;
import com.example.GameLoveApi.model.GameLove;
import com.example.GameLoveApi.model.Player;
import com.example.GameLoveApi.repository.GameLoveRepository;
import com.example.GameLoveApi.repository.GameRepository;
import com.example.GameLoveApi.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class GameLoveServiceTest
{

    @Mock
    private GameLoveRepository gameLoveRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameLoveService gameLoveService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoveGame()
    {
        PlayerDTO playerDTO = new PlayerDTO(1L, "Player1");
        Game game = new Game("Game 1");
        game.setId(1L);
        Player player = new Player();
        player.setId(1L);
        player.setUsername("Player1");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(playerRepository.save(any(Player.class))).thenReturn(player);
        when(gameLoveRepository.findByPlayerAndGame(player, game)).thenReturn(Optional.empty());

        GameLove gameLove = new GameLove();
        gameLove.setPlayer(player);
        gameLove.setGame(game);
        gameLove.setCreatedAt(LocalDateTime.now());

        when(gameLoveRepository.save(any(GameLove.class))).thenReturn(gameLove);

        GameLoveDTO result = gameLoveService.loveGame(playerDTO, 1L);

        assertNotNull(result);
        assertEquals("Player1", result.getPlayer().getUsername());
        assertEquals("Game 1", result.getGame().getTitle());
    }
}