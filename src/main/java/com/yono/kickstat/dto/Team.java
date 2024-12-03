package com.yono.kickstat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Team {
    private Long id;
    private String name;
    private String tla;  // 3글자 약자 (e.g. ARS, MUN)
    private String shortName;
    private String crest;  // 팀 로고 URL
}
