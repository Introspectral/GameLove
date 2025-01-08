package com.example.GameLoveApi.repository;

import com.example.GameLoveApi.model.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long>
{
    // Query to find the most loved games
    @Query("SELECT g, COUNT(gl) as loveCount FROM Game g LEFT JOIN g.lovingPlayers gl GROUP BY g ORDER BY loveCount DESC")
    List<Object[]> findMostLovedGames(Pageable pageable);
}