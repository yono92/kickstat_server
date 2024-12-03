package com.yono.kickstat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "matches")
public class Match {

    @Id
    private Long id;

    @Column(nullable = false)
    private String homeTeamName;

    @Column(nullable = false)
    private String awayTeamName;

    private Integer homeScore;
    private Integer awayScore;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Column(nullable = false)
    private String competition;

    @Builder
    public Match(Long id, String homeTeamName, String awayTeamName,
                 Integer homeScore, Integer awayScore, String status,
                 LocalDateTime matchDate, String competition) {
        this.id = id;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.status = status;
        this.matchDate = matchDate;
        this.competition = competition;
    }
}
