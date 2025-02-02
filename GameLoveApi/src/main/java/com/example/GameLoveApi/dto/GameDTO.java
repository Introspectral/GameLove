package com.example.GameLoveApi.dto;

public class GameDTO
{
    private Long id;
    private String title;
    private int loveCount;


    public GameDTO(Long id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public GameDTO(Long id, String title, int loveCount)
    {
        this.id = id;
        this.title = title;
        this.loveCount = loveCount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getLoveCount() { return loveCount; }
    public void setLoveCount(int loveCount) { this.loveCount = loveCount; }
}