package com.yono.kickstat.client;

import com.yono.kickstat.dto.MatchResponse;
import com.yono.kickstat.dto.TeamDetailResponse;
import com.yono.kickstat.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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

    public TeamDetailResponse getTeamDetail(Long teamId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Auth-Token", apiKey);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            return restTemplate.exchange(
                    baseUrl + "/teams/" + teamId,
                    HttpMethod.GET,
                    entity,
                    TeamDetailResponse.class
            ).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Team not found with id: " + teamId);
        }
    }


    public MatchResponse getTeamMatches(Long teamId, String status, String dateFrom, String dateTo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = baseUrl + "/teams/" + teamId + "/matches";
        url += "?status=" + status;
        if (dateFrom != null) url += "&dateFrom=" + dateFrom;
        if (dateTo != null) url += "&dateTo=" + dateTo;

        ResponseEntity<MatchResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                MatchResponse.class
        );

        return response.getBody();
    }


}
