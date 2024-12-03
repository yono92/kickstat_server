package com.yono.kickstat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public class StandingResponse {
    private List<StandingData> standings;
}
