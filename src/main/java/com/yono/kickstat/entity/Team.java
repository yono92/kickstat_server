package com.yono.kickstat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teams")
public class Team {
    @Id
    private Long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private String venue;
    private String website;
    private String founded;
    private String clubColors;

    @Builder
    public Team(Long id, String name, String shortName, String tla,
                String crest, String venue, String website,
                String founded, String clubColors) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.tla = tla;
        this.crest = crest;
        this.venue = venue;
        this.website = website;
        this.founded = founded;
        this.clubColors = clubColors;
    }
}
