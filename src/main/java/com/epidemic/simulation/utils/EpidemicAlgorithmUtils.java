package com.epidemic.simulation.utils;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EpidemicAlgorithmUtils {

    public double calculateInfectedPeople(Simulation sim, int day, List<Report> reports) {
        Report rep = reports.get(day - 1);
        if (day == 0) {
            return rep.getNumberOfHealthyPeopleSusceptibleToInfection() * sim.getVirusReproductionRate() / 100
                    + rep.getNumberOfInfected() - rep.getNumberOfDeaths();
        } else {
            return (reports.get(rep.getDay()).getNumberOfHealthyPeopleSusceptibleToInfection() * sim.getVirusReproductionRate() / 100
                    + reports.get(rep.getDay()).getNumberOfInfected()) - rep.getNumberOfDeaths();
        }
    }

    public double calculateHealthyPeopleSusceptibleToInfection(Simulation sim, int day, List<Report> reports) {
        return sim.getPopulationQuantity() - calculateInfectedPeople(sim, day, reports)
                - calculateDeadPeople(sim, day, reports) - calculateRecoveredPeople(sim, day, reports);
    }

    public double calculateDeadPeople(Simulation sim, int day, List<Report> reports) {
        if (day - sim.getDaysFromInfectionToDeath() <= 0) {
            return 0;
        } else {
            return reports.get(day - sim.getDaysFromInfectionToDeath()).getNumberOfInfected() * sim.getMortalityRate() / 100;
        }
    }

    public double calculateRecoveredPeople(Simulation sim, int day, List<Report> reports) {
        if (day - sim.getDaysFromInfectionToRecovery() < 0) {
            return 0;
        } else {
            return reports.get(day - sim.getDaysFromInfectionToRecovery()).getNumberOfInfected();
        }
    }
}
