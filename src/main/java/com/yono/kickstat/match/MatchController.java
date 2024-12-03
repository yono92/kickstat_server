package com.yono.kickstat.match;

import com.yono.kickstat.entity.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
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
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }
}
