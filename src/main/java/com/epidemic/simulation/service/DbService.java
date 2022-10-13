package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import com.epidemic.simulation.repository.ReportRepository;
import com.epidemic.simulation.repository.SimulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbService {

    private final SimulationRepository simulationRepository;
    private final ReportRepository reportRepository;

    public void saveSimulation(final Simulation simulation) {
        simulationRepository.save(simulation);
    }

    public void saveReport(final Report report) {
        reportRepository.save(report);
    }
}
