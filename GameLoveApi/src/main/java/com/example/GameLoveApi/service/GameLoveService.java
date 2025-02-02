package com.example.GameLoveApi.service;

import com.example.GameLoveApi.dto.GameDTO;
import com.example.GameLoveApi.dto.GameLoveDTO;
import com.example.GameLoveApi.dto.PlayerDTO;
import com.example.GameLoveApi.mapper.GameLoveMapper;
import com.example.GameLoveApi.mapper.GameMapper;
import com.example.GameLoveApi.mapper.PlayerMapper;
import com.example.GameLoveApi.model.Game;
import com.example.GameLoveApi.model.GameLove;
import com.example.GameLoveApi.model.Player;
import com.example.GameLoveApi.repository.GameLoveRepository;
import com.example.GameLoveApi.repository.GameRepository;
import com.example.GameLoveApi.repository.PlayerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameLoveService
{

    private final GameLoveRepository gameLoveRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameLoveService(GameLoveRepository gameLoveRepository, GameRepository gameRepository, PlayerRepository playerRepository)
    {
        this.gameLoveRepository = gameLoveRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameLoveDTO loveGame(PlayerDTO playerDTO, Long gameId)
    {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));

        Player player = PlayerMapper.toEntity(playerDTO);
        player = playerRepository.save(player);

        Optional<GameLove> existingLove = gameLoveRepository.findByPlayerAndGame(player, game);
        if (existingLove.isPresent())
        {
            return GameLoveMapper.toDTO(existingLove.get());
        }

        GameLove gameLove = new GameLove();
        gameLove.setPlayer(player);
        gameLove.setGame(game);
        gameLove.setCreatedAt(LocalDateTime.now());

        return GameLoveMapper.toDTO(gameLoveRepository.save(gameLove));
    }

    public List<GameDTO> getLovedGames(Long playerId)
    {
        Player player = playerRepository.findById(playerId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid player ID"));

        return player.getLovedGames().stream()
            .map(GameLove::getGame)
            .map(GameMapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<GameDTO> getMostLovedGames(int limit)
    {
        return gameRepository.findMostLovedGames(PageRequest.of(0, limit))
            .stream()
            .map(result -> GameMapper.toDTOWithLoveCount((Game) result[0], (int) result[1]))
            .collect(Collectors.toList());
    }

    public List<GameDTO> getAllGames()
    {
        return gameRepository.findAll().stream()
            .map(GameMapper::toDTO)
            .collect(Collectors.toList());
    }
}