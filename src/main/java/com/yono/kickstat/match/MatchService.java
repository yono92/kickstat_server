package com.yono.kickstat.match;

import com.yono.kickstat.client.FootballDataClient;
import com.yono.kickstat.dto.MatchResponse;
import com.yono.kickstat.entity.Match;
import com.yono.kickstat.exception.FootballApiException;
import com.yono.kickstat.utils.MatchConverter;
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
public class MatchService {
    private final FootballDataClient footballDataClient;
    private final MatchRepository matchRepository;

    @Scheduled(cron = "0 0 */6 * * *")
    public void updateMatches() {
        List<String> competitions = Arrays.asList("PL", "PD", "BL1");

        for (String competition : competitions) {
            MatchResponse response = footballDataClient.getMatches(competition);
            List<Match> matches = response.getMatches().stream()
                    .map(MatchConverter::toEntity)
                    .collect(Collectors.toList());

            matchRepository.saveAll(matches);
        }
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

}
