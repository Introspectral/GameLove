package com.example.GameLoveApi.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.GameLoveApi.dto.GameDTO;
import com.example.GameLoveApi.dto.GameLoveDTO;
import com.example.GameLoveApi.dto.PlayerDTO;
import com.example.GameLoveApi.service.GameLoveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class GameLoveControllerTest
{

    @Mock
    private GameLoveService gameLoveService;

    @InjectMocks
    private GameLoveController gameLoveController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gameLoveController).build();
    }

    @Test
    public void testLoveGame() throws Exception
    {
        PlayerDTO playerDTO = new PlayerDTO(1L, "Player1");
        GameLoveDTO gameLoveDTO = new GameLoveDTO(1L, playerDTO, new GameDTO(1L, "Game 1"), null);

        when(gameLoveService.loveGame(any(PlayerDTO.class), anyLong())).thenReturn(gameLoveDTO);

        mockMvc.perform(post("/api/games/1/love")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"username\":\"Player1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player.username").value("Player1"))
                .andExpect(jsonPath("$.game.title").value("Game 1"));
    }

    @Test
    public void testGetLovedGames() throws Exception
    {
        when(gameLoveService.getLovedGames(anyLong())).thenReturn(Collections.singletonList(new GameDTO(1L, "Game 1")));

        mockMvc.perform(get("/api/games/player/1/loved"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Game 1"));
    }

    @Test
    public void testGetMostLovedGames() throws Exception
    {
        when(gameLoveService.getMostLovedGames(anyInt())).thenReturn(Collections.singletonList(new GameDTO(1L, "Game 1")));

        mockMvc.perform(get("/api/games/most-loved?limit=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Game 1"));
    }

    @Test
    public void testGetAllGames() throws Exception
    {
        when(gameLoveService.getAllGames()).thenReturn(Collections.singletonList(new GameDTO(1L, "Game 1")));

        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Game 1"));
    }
}