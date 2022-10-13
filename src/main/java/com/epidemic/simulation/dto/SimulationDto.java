package com.epidemic.simulation.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class SimulationDto {
    private Long id;
    private String simulationName;
    private Integer populationQuantity;
    private Integer initialNumberOfInfected;
    private Double virusReproductionRate;
    private Double mortalityRate;
    private Integer daysFromInfectionToRecovery;
    private Integer daysFromInfectionToDeath;
    private Integer simulationDuration;
}
