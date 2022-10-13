package com.epidemic.simulation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SIMULATIONS")
public class Simulation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String simulationName;

    @NotNull
    @Column(name = "POPULATION_QUANTITY")
    private Integer populationQuantity;

    @NotNull
    @Column(name = "INITIAL_NUMBER_OF_INFECTED")
    private Integer initialNumberOfInfected;

    @NotNull
    @Column(name = "VIRUS_REPRODUCTION_RATE")
    private Double virusReproductionRate;

    @NotNull
    @Column(name = "MORTALITY_RATE")
    private Double mortalityRate;

    @NotNull
    @Column(name = "DAYS_FROM_INFECTION_TO_RECOVERY")
    private Integer daysFromInfectionToRecovery;

    @NotNull
    @Column(name = "DAYS_FROM_INFECTION_TO_DEATH")
    private Integer daysFromInfectionToDeath;

    @NotNull
    @Column(name = "SIMULATION_DURATION_IN_DAYS")
    private Integer simulationDuration;
}
