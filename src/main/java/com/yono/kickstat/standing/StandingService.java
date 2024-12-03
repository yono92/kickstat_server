package com.yono.kickstat.standing;

import com.yono.kickstat.client.StandingClient;
import com.yono.kickstat.dto.Standing;
import com.yono.kickstat.dto.StandingResponse;
import com.yono.kickstat.dto.TableEntry;
import com.yono.kickstat.utils.StandingConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StandingService {
    private final StandingClient standingClient;
    private final StandingRepository standingRepository;

    @Scheduled(cron = "0 0 */12 * * *")
    @Transactional
    public void updateStandings() {
        List<String> competitions = Arrays.asList("PL", "PD", "BL1");

        for (String competition : competitions) {
            StandingResponse response = standingClient.getStandings(competition);
            List<Standing> standings = response.getStandings()
                    .stream()
                    .flatMap(standingData -> standingData.getTable().stream())
                    .map(entry -> StandingConverter.toEntity(entry, competition))
                    .collect(Collectors.toList());

            standingRepository.deleteByCompetition(competition);
            standingRepository.saveAll(standings);
        }
    }
    @Transactional
    public List<Standing> getStandingsByCompetition(String competition) {
        return standingRepository.findByCompetitionOrderByPosition(competition);
    }
}