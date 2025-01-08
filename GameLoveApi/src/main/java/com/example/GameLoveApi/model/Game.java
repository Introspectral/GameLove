package com.example.GameLoveApi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "game")
    private Set<GameLove> lovingPlayers = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Set<GameLove> getLovingPlayers() { return lovingPlayers; }
    public void setLovingPlayers(Set<GameLove> lovingPlayers) { this.lovingPlayers = lovingPlayers; }

    public Game() {}

    public Game(String title) {
        this.title = title;
    }
}