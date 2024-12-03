package com.yono.kickstat.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TableEntry {
    private Integer position;
    private Team team;  // Team 객체
    private Integer playedGames;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
}
