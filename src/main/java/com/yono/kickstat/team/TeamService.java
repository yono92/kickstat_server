package com.yono.kickstat.team;

import com.yono.kickstat.client.FootballDataClient;
import com.yono.kickstat.dto.MatchResponse;
import com.yono.kickstat.dto.TeamDetailResponse;
import com.yono.kickstat.entity.Team;
import com.yono.kickstat.exception.ResourceNotFoundException;
import com.yono.kickstat.utils.TeamConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final FootballDataClient footballDataClient;

    @Transactional
    public Team getTeamDetail(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseGet(() -> {
                    TeamDetailResponse response = footballDataClient.getTeamDetail(teamId);
                    Team team = TeamConverter.toEntity(response);
                    return teamRepository.save(team);
                });
    }



    public MatchResponse getTeamMatches(Long teamId, String status, String dateFrom, String dateTo) {
        // DB에서 팀 확인 (존재하지 않으면 예외 처리)
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));

        // Football Data API를 통해 팀 일정 가져오기
        return footballDataClient.getTeamMatches(team.getId(), status, dateFrom, dateTo);
    }
}
