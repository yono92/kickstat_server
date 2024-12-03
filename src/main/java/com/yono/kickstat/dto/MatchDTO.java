package com.yono.kickstat.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private Competition competition;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    private String status;
    private String utcDate;
}
