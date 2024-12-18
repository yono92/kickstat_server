package com.yono.kickstat.utils;

import com.yono.kickstat.dto.TeamDetailResponse;
import com.yono.kickstat.entity.Team;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TeamConverter {
    public static Team toEntity(TeamDetailResponse response) {
        return Team.builder()
                .id(response.getId())
                .name(response.getName())
                .shortName(response.getShortName())
                .tla(response.getTla())
                .crest(response.getCrest())
                .venue(response.getVenue())
                .website(response.getWebsite())
                .founded(response.getFounded())
                .clubColors(response.getClubColors())
                .build();
    }


    public static TeamDetailResponse toResponse(Team team) {
        return TeamDetailResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .shortName(team.getShortName())
                .tla(team.getTla())
                .crest(team.getCrest())
                .venue(team.getVenue())
                .website(team.getWebsite())
                .founded(team.getFounded())
                .clubColors(team.getClubColors())
                .build();
    }
}
