package com.yono.kickstat.client;

import com.yono.kickstat.dto.StandingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class StandingClient {

    private final RestTemplate restTemplate;

    @Value("${football-data.api-key}")
    private String apiKey;

    @Value("${football-data.base-url}")
    private String baseUrl;

    public StandingResponse getStandings(String competitionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/competitions/" + competitionId + "/standings",
                HttpMethod.GET,
                entity,
                StandingResponse.class
        ).getBody();
    }
}
