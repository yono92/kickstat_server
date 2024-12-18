package com.yono.kickstat.match;

import com.yono.kickstat.entity.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matches")  // 이 부분이 필요
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/update")
    public ResponseEntity<String> updateMatches() {
        try {
            matchService.updateMatches();
            return ResponseEntity.ok("Match data updated successfully");
        } catch (Exception e) {
            log.error("Failed to update match data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update match data");
        }
    }

    @GetMapping
    public ResponseEntity<List<Match>> getMatches() {
        LocalDate today = LocalDate.now();
        // 오늘 날짜 기준 +-7일의 경기를 가져옴
        List<Match> matches = matchService.getMatchesByDateRange(
                today.minusDays(7),
                today.plusDays(7)
        );
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Match>> getMatchesByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Match> matches = matchService.getMatchesByDate(date);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Match>> getMatchesByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        List<Match> matches = matchService.getMatchesByDateRange(start, end);
        return ResponseEntity.ok(matches);
    }
}
