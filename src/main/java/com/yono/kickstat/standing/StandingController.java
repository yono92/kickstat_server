package com.yono.kickstat.standing;

import com.yono.kickstat.entity.Standing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/standings")
public class StandingController {
    private final StandingService standingService;

    @PostMapping("/update")
    public ResponseEntity<String> updateStandings() {
        standingService.updateStandings();
        return ResponseEntity.ok("Standings updated successfully");
    }

    @GetMapping("/{competition}")
    public ResponseEntity<List<Standing>> getStandings(@PathVariable String competition) {
        return ResponseEntity.ok(standingService.getStandingsByCompetition(competition));
    }
}
