package com.example.GameLoveApi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "player")
    private Set<GameLove> lovedGames = new HashSet<>();

    public Player() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Set<GameLove> getLovedGames() { return lovedGames; }
    public void setLovedGames(Set<GameLove> lovedGames) { this.lovedGames = lovedGames; }
}