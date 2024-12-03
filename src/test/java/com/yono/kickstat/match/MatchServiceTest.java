package com.yono.kickstat.match;

import com.yono.kickstat.entity.Match;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class MatchServiceTest {
    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;


    @Test
    @DisplayName("매치 데이터 업데이트 테스트")
    void updateMatches() {
        // when
        matchService.updateMatches();

        // then
        List<Match> matches = matchRepository.findAll();
        assertFalse(matches.isEmpty());

        // 데이터 검증
        Match match = matches.get(0);
        assertNotNull(match.getId());
        assertNotNull(match.getHomeTeamName());
        assertNotNull(match.getAwayTeamName());
        assertNotNull(match.getMatchDate());
    }
}
