package com.yono.kickstat.standing;

import com.yono.kickstat.entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {
    List<Standing> findByCompetitionOrderByPosition(String competition);
    void deleteByCompetition(String competition);
    Optional<Standing> findByApiId(Long apiId); // API ID로 데이터를 검색하는 메서드 추가
}

