package com.yono.kickstat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MatchResponse {
    private List<MatchDTO> matches;
}
