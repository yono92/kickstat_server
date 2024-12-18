package com.yono.kickstat.team;

import com.yono.kickstat.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TeamServiceIntegrationTest {
    @Autowired
    private TeamService teamService;


    @Test
    void getTeamDetail() {
        // when
        Team team = teamService.getTeamDetail(57L); // Arsenal ID

        // then
        assertNotNull(team);
        assertEquals("Arsenal FC", team.getName());
        // 실제 데이터 검증
    }
}
