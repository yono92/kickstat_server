package com.yono.kickstat.team;

import com.yono.kickstat.dto.MatchResponse;
import com.yono.kickstat.dto.TeamDetailResponse;
import com.yono.kickstat.entity.Team;
import com.yono.kickstat.utils.TeamConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // 팀 상세 정보 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<TeamDetailResponse> getTeamDetail(@PathVariable Long id) {
        Team team = teamService.getTeamDetail(id);
        return ResponseEntity.ok(TeamConverter.toResponse(team));  // Entity를 Response DTO로 변환
    }

    // 팀 일정 가져오기
    @GetMapping("/{id}/matches")
    public ResponseEntity<MatchResponse> getTeamMatches(
            @PathVariable Long id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo
    ) {
        MatchResponse matches = teamService.getTeamMatches(id, status, dateFrom, dateTo);
        return ResponseEntity.ok(matches);
    }
}
