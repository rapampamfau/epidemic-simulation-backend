package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import com.epidemic.simulation.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportFiller {

    private final EpidemicAlgorithm epidemicAlgorithm;
    private final ReportRepository reportRepository;

    private void insertData(Simulation simulation, Report report, List<Report> reports) {
        report.setNumberOfDeaths((int) epidemicAlgorithm.calculateDeadPeople(simulation, report, reports));
        report.setNumberOfInfected((int) epidemicAlgorithm.calculateInfectedPeople(simulation, report, reports));
        report.setNumberOfHealthyPeopleSusceptibleToInfection((int) epidemicAlgorithm.calculateHealthyPeopleSusceptibleToInfection(simulation, report, reports));
        report.setNumberOfPeopleWhoAcquiredImmunity((int) epidemicAlgorithm.calculateRecoveredPeople(simulation, report, reports));
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public void fillReportsWithData(Simulation simulation, List<Report> reports) {
        for (Report report : reports) {
            if (report.getDay() > 0) {
                insertData(simulation, report , reports);
            }
        }
    }
}
