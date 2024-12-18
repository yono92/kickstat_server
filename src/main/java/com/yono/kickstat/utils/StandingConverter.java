package com.yono.kickstat.utils;

import com.yono.kickstat.entity.Standing;
import com.yono.kickstat.dto.TableEntry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StandingConverter {
    public static Standing toEntity(TableEntry entry, String competition) {
        return Standing.builder()
                .apiId(entry.getTeam().getId()) // API에서 팀의 ID를 매핑
                .competition(competition)
                .teamName(entry.getTeam().getName())
                .position(entry.getPosition())
                .playedGames(entry.getPlayedGames())
                .won(entry.getWon())
                .draw(entry.getDraw())
                .lost(entry.getLost())
                .points(entry.getPoints())
                .goalsFor(entry.getGoalsFor())
                .goalsAgainst(entry.getGoalsAgainst())
                .build();
    }
}