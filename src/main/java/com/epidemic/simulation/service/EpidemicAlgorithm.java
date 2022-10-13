package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpidemicAlgorithm {

    public double calculateInfectedPeople(Simulation sim, Report rep, List<Report> reports) {
        if (rep.getDay() == 0) {
            return rep.getNumberOfHealthyPeopleSusceptibleToInfection() * sim.getVirusReproductionRate() / 100
                    + rep.getNumberOfInfected() - rep.getNumberOfDeaths();
        } else {
            return (reports.get(rep.getDay() - 1).getNumberOfHealthyPeopleSusceptibleToInfection() * sim.getVirusReproductionRate() / 100
                    + reports.get(rep.getDay() - 1).getNumberOfInfected()) - rep.getNumberOfDeaths();
        }
    }

    public double calculateHealthyPeopleSusceptibleToInfection(Simulation sim, Report rep, List<Report> reports) {
        return sim.getPopulationQuantity() - calculateInfectedPeople(sim, rep, reports)
                - calculateDeadPeople(sim, rep, reports) - calculateRecoveredPeople(sim, rep, reports);
    }

    public double calculateDeadPeople(Simulation sim, Report rep, List<Report> reports) {
        if (rep.getDay() - sim.getDaysFromInfectionToDeath() <= 0) {
            return 0;
        } else {
            return reports.get(rep.getDay() - sim.getDaysFromInfectionToDeath()).getNumberOfInfected() * sim.getMortalityRate() / 100;
        }
    }

    public double calculateRecoveredPeople(Simulation sim, Report rep, List<Report> reports) {
        if (rep.getDay() - sim.getDaysFromInfectionToRecovery() <= 0) {
            return 0;
        } else {
            return reports.get(rep.getDay() - sim.getDaysFromInfectionToRecovery()).getNumberOfInfected();
        }
    }
}
