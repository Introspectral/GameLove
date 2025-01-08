package com.example.GameLoveApi.service;

import com.example.GameLoveApi.model.Game;
import com.example.GameLoveApi.model.GameLove;
import com.example.GameLoveApi.model.Player;
import com.example.GameLoveApi.repository.GameLoveRepository;
import com.example.GameLoveApi.repository.GameRepository;
import com.example.GameLoveApi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameLoveService
{
    @Autowired
    private GameLoveRepository gameLoveRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public GameLove loveGame(Player player, Long gameId)
    {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));
        player = playerRepository.save(player);
        Optional<GameLove> existingLove = gameLoveRepository.findByPlayerAndGame(player, game);
        if (existingLove.isPresent())
        {
            return existingLove.get();
        }

        GameLove gameLove = new GameLove();
        gameLove.setPlayer(player);
        gameLove.setGame(game);
        gameLove.setCreatedAt(LocalDateTime.now());
        return gameLoveRepository.save(gameLove);
    }

    public void unloveGame(Player player, Long gameId)
    {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));
        gameLoveRepository.findByPlayerAndGame(player, game)
                .ifPresent(gameLoveRepository::delete);
    }

    public List<Game> getLovedGames(Long playerId)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("Invalid player ID"));
        return player.getLovedGames().stream().map(GameLove::getGame).collect(Collectors.toList());
    }

    public List<Object[]> getMostLovedGames(int limit) {
        return gameRepository.findMostLovedGames(PageRequest.of(0, limit));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}