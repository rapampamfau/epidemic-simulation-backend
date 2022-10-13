package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import com.epidemic.simulation.dto.SimulationDto;
import com.epidemic.simulation.mapper.SimulationMapper;
import com.epidemic.simulation.utils.EpidemicAlgorithmUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportGeneratorService {

    private final List<Report> reports = new ArrayList<>();
    private final SimulationMapper simulationMapper;
    private final EpidemicAlgorithmUtils epidemicAlgorithmUtils;
    private final DbService dbService;

    private Report generateFirstReport(Simulation simulation) {
        double infected = simulation.getInitialNumberOfInfected();
        double healthySusceptibleToInfection = simulation.getPopulationQuantity() - infected;

        return Report.builder()
                .day(0)
                .numberOfInfected(infected)
                .numberOfHealthyPeopleSusceptibleToInfection(healthySusceptibleToInfection)
                .numberOfDeaths(0.0)
                .numberOfPeopleWhoAcquiredImmunity(0.0)
                .build();
    }

    public List<Report> generateReports(SimulationDto simDto) {
        for (int day = 0; day < simDto.getSimulationDuration(); day++) {
            Simulation sim = simulationMapper.mapToSimulation(simDto);
            Report report;
            if (day == 0) {
                report = generateFirstReport(sim);
            } else {
                report = Report.builder()
                        .day(day)
                        .numberOfDeaths(epidemicAlgorithmUtils.calculateDeadPeople(sim, day, reports))
                        .numberOfInfected(epidemicAlgorithmUtils.calculateInfectedPeople(sim, day, reports))
                        .numberOfHealthyPeopleSusceptibleToInfection(epidemicAlgorithmUtils.calculateHealthyPeopleSusceptibleToInfection(sim,  day, reports))
                        .numberOfPeopleWhoAcquiredImmunity(epidemicAlgorithmUtils.calculateRecoveredPeople(sim, day, reports))
                        .build();
            }
            reports.add(report);
            dbService.saveReport(report);
        }
        dbService.saveSimulation(simulationMapper.mapToSimulation(simDto));
        return reports;
    }

    public List<Report> getReports() {
        return reports;
    }
}
