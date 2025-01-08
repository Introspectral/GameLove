package com.example.GameLoveApi.repository;

import com.example.GameLoveApi.model.GameLove;
import com.example.GameLoveApi.model.Player;
import com.example.GameLoveApi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameLoveRepository extends JpaRepository<GameLove, Long>
{
    Optional<GameLove> findByPlayerAndGame(Player player, Game game);
}
