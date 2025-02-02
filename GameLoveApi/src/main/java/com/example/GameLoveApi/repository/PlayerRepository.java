package com.example.GameLoveApi.repository;

import com.example.GameLoveApi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {}
