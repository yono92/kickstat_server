package com.yono.kickstat.client;

import com.yono.kickstat.dto.MatchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FootballDataClient {
    private final RestTemplate restTemplate;


    @Value("${football-data.api-key}")
    private String apiKey;

    @Value("${football-data.base-url}")
    private String baseUrl;

    public MatchResponse getMatches(String competitionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<MatchResponse> response = restTemplate.exchange(
                baseUrl + "/competitions/" + competitionId + "/matches",
                HttpMethod.GET,
                entity,
                MatchResponse.class
        );

        return response.getBody();
    }
}
