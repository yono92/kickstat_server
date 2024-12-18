package com.yono.kickstat.standing;

import com.yono.kickstat.client.StandingClient;
import com.yono.kickstat.entity.Standing;
import com.yono.kickstat.dto.StandingResponse;
import com.yono.kickstat.utils.StandingConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingService {
    private final StandingClient standingClient;
    private final StandingRepository standingRepository;

    @Scheduled(cron = "0 0 */12 * * *")
    @Transactional
    public void updateStandings() {
        List<String> competitions = Arrays.asList("PL", "PD", "BL1");

        for (String competition : competitions) {
            StandingResponse response = standingClient.getStandings(competition);

            log.info("스탠딩 :  ", response.toString());

            List<Standing> standings = response.getStandings()
                    .stream()
                    .flatMap(standingData -> standingData.getTable().stream())
                    .map(entry -> StandingConverter.toEntity(entry, competition))
                    .collect(Collectors.toList());

            for (Standing newStanding : standings) {
                standingRepository.findByApiId(newStanding.getApiId())
                        .ifPresentOrElse(existingStanding -> {
                            // 기존 데이터를 업데이트
                            existingStanding.updateStanding(
                                    newStanding.getPosition(),
                                    newStanding.getPlayedGames(),
                                    newStanding.getWon(),
                                    newStanding.getDraw(),
                                    newStanding.getLost(),
                                    newStanding.getPoints(),
                                    newStanding.getGoalsFor(),
                                    newStanding.getGoalsAgainst()
                            );
                            standingRepository.save(existingStanding);
                        }, () -> {
                            // 새 데이터를 삽입
                            standingRepository.save(newStanding);
                        });
            }
        }
    }
    @Transactional
    public List<Standing> getStandingsByCompetition(String competition) {
        return standingRepository.findByCompetitionOrderByPosition(competition);
    }
}