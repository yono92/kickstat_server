package com.yono.kickstat.utils;

import com.yono.kickstat.dto.Standing;
import com.yono.kickstat.dto.TableEntry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StandingConverter {
    public static Standing toEntity(TableEntry entry, String competition) {
        return Standing.builder()
                .competition(competition)
                .teamName(entry.getTeam().getName())  // Team 객체에서 이름 가져오기
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