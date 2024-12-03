package com.yono.kickstat.match;

import com.yono.kickstat.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByCompetition(String competition);
    List<Match> findByMatchDateBetween(LocalDateTime start, LocalDateTime end);
}
