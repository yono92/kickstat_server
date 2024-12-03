package com.yono.kickstat.dto;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "standings")
public class Standing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String competition;
    private String teamName;
    private Integer position;
    private Integer playedGames;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;

    @Builder
    public Standing(String competition, String teamName, Integer position,
                    Integer playedGames, Integer won, Integer draw, Integer lost,
                    Integer points, Integer goalsFor, Integer goalsAgainst) {
        this.competition = competition;
        this.teamName = teamName;
        this.position = position;
        this.playedGames = playedGames;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }
}
