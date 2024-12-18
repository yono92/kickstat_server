package com.yono.kickstat.team;

import com.yono.kickstat.client.FootballDataClient;
import com.yono.kickstat.dto.TeamDetailResponse;
import com.yono.kickstat.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamServiceTest {
    @Mock
    private FootballDataClient footballDataClient;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    @DisplayName("DB에 팀 정보가 있으면 DB에서 조회한다")
    void getTeamDetail_FromDB() {
        // given
        Long teamId = 57L;
        Team team = Team.builder()
                .id(teamId)
                .name("Arsenal FC")
                .tla("ARS")
                .venue("Emirates Stadium")
                .build();

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // when
        Team result = teamService.getTeamDetail(teamId);

        // then
        assertNotNull(result);
        assertEquals("Arsenal FC", result.getName());
        verify(teamRepository).findById(teamId);
        verify(footballDataClient, never()).getTeamDetail(any());
    }

    @Test
    @DisplayName("DB에 팀 정보가 없으면 API를 호출하여 저장한다")
    void getTeamDetail_FromAPI() {
        // given
        Long teamId = 57L;
        TeamDetailResponse apiResponse = TeamDetailResponse.builder()
                .id(teamId)
                .name("Arsenal FC")
                .build();

        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());
        when(footballDataClient.getTeamDetail(teamId)).thenReturn(apiResponse);
        when(teamRepository.save(any(Team.class))).thenAnswer(i -> i.getArgument(0));

        // when
        Team result = teamService.getTeamDetail(teamId);

        // then
        assertNotNull(result);
        assertEquals("Arsenal FC", result.getName());
        verify(teamRepository).findById(teamId);
        verify(footballDataClient).getTeamDetail(teamId);
        verify(teamRepository).save(any(Team.class));
    }
}
