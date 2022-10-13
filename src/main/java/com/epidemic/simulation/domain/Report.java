package com.epidemic.simulation.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REPORTS")
public class Report {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "DAY_")
    private Integer day;

    @NotNull
    @Column(name = "INFECTED_PEOPLE")
    private Double numberOfInfected;

    @NotNull
    @Column(name = "HEALTHY_PEOPLE_SUSCEPTIBLE_TO_INFECTION")
    private Double numberOfHealthyPeopleSusceptibleToInfection;

    @NotNull
    @Column(name = "DEATHS")
    private Double numberOfDeaths;

    @NotNull
    @Column(name = "PEOPLE_WHO_ACQUIRED_IMMUNITY")
    private Double numberOfPeopleWhoAcquiredImmunity;
}
