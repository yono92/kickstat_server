package com.yono.kickstat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamDetailResponse {
    private Long id;
    private String name;
    private String shortName;
    private String tla;  // 세글자 약자 (예: ARS)
    private String crest;  // 팀 로고 URL
    private String venue;  // 홈 구장
    private String website;
    private String founded;  // 창단년도
    private String clubColors;


    @Builder
    public TeamDetailResponse(Long id, String name, String shortName, String tla,
                              String crest, String venue, String website,
                              String founded, String clubColors) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.tla = tla;
        this.crest = crest;
        this.venue = venue;
        this.website = website;
        this.founded = founded;
        this.clubColors = clubColors;
    }

}
