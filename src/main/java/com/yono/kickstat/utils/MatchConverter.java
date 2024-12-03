package com.yono.kickstat.utils;

import com.yono.kickstat.dto.MatchDTO;
import com.yono.kickstat.entity.Match;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;

@UtilityClass
public class MatchConverter {
    public static Match toEntity(MatchDTO dto) {
        return Match.builder()
                .id(dto.getId())
                .homeTeamName(dto.getHomeTeam().getName())
                .awayTeamName(dto.getAwayTeam().getName())
                .homeScore(dto.getScore().getFullTime().getHome())
                .awayScore(dto.getScore().getFullTime().getAway())
                .status(dto.getStatus())
                .matchDate(Instant.parse(dto.getUtcDate()).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .competition(dto.getCompetition().getCode())
                .build();
    }
}
