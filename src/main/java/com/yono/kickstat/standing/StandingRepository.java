package com.yono.kickstat.standing;

import com.yono.kickstat.dto.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {
    List<Standing> findByCompetitionOrderByPosition(String competition);
    void deleteByCompetition(String competition);
}

