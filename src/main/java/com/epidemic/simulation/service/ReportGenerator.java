package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportGenerator {

    private final List<Report> reports = new ArrayList<>();

    private Report generateFirstReport(Simulation simulation) {
        int infected = simulation.getInitialNumberOfInfected();
        int healthySusceptibleToInfection = simulation.getPopulationQuantity() - infected;

        return Report.builder()
                .day(0)
                .numberOfInfected(infected)
                .numberOfHealthyPeopleSusceptibleToInfection(healthySusceptibleToInfection)
                .numberOfDeaths(0)
                .numberOfPeopleWhoAcquiredImmunity(0)
                .build();
    }

    public void generateReports(Simulation sim, List<Report> reports) {
        for (int i = 0; i < sim.getSimulationDuration() + 1; i++) {
            Report report;
            if (i == 0) {
                report = generateFirstReport(sim);
            } else {
                report = new Report();
                report.setDay(i);
            }
            reports.add(report);
        }
    }

    public List<Report> getReports() {
        return reports;
    }
}
