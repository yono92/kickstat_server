package com.yono.kickstat.standing;

import com.yono.kickstat.entity.Standing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class StandingServiceTest {
    @Autowired
    private StandingService standingService;

    @Autowired
    private StandingRepository standingRepository;

    @Test
    @DisplayName("순위 데이터 업데이트 테스트")
    void updateStandings() {
        // when
        standingService.updateStandings();

        // then
        List<Standing> standings = standingRepository.findAll();
        assertFalse(standings.isEmpty());

        Standing standing = standings.get(0);
        assertNotNull(standing.getTeamName());
        assertNotNull(standing.getPosition());
        assertNotNull(standing.getPoints());
        assertTrue(standing.getPlayedGames() >= 0);
        assertTrue(standing.getPoints() >= 0);
    }

    @Test
    @DisplayName("특정 리그 순위 조회 테스트")
    void getStandingsByCompetition() {
        // given
        String competition = "PL";
        standingService.updateStandings();

        // when
        List<Standing> standings = standingService.getStandingsByCompetition(competition);

        // then
        assertFalse(standings.isEmpty());
        assertEquals(competition, standings.get(0).getCompetition());
        assertTrue(standings.get(0).getPosition() <= standings.get(1).getPosition());
    }
}
