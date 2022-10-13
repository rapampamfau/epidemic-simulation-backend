package com.epidemic.simulation.mapper;

import com.epidemic.simulation.domain.Simulation;
import com.epidemic.simulation.dto.SimulationDto;
import org.springframework.stereotype.Service;

@Service
public class SimulationMapper {

    public Simulation mapToSimulation(final SimulationDto simulationDto) {
        return new Simulation(
                simulationDto.getId(),
                simulationDto.getSimulationName(),
                simulationDto.getPopulationQuantity(),
                simulationDto.getInitialNumberOfInfected(),
                simulationDto.getVirusReproductionRate(),
                simulationDto.getMortalityRate(),
                simulationDto.getDaysFromInfectionToRecovery(),
                simulationDto.getDaysFromInfectionToDeath(),
                simulationDto.getSimulationDuration()
        );
    }
}
