package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpidemicAlgorithmTestSuite {

    private final EpidemicAlgorithm epidemicAlgorithm = new EpidemicAlgorithm();

    @Test
    void testCalculateInfected() {
        //Given
        Simulation simulation = new Simulation(
                1L,
                "TestName",
                10000,
                1000,
                2.0,
                2.0,
                2,
                1,
                100
        );
        Report firstReport = new Report(
                1L,
                0,
                1000,
                9000,
                0,
                0
        );
        List<Report> reports = new ArrayList<>();
        reports.add(firstReport);

        //When
        double result = epidemicAlgorithm.calculateInfectedPeople(simulation, firstReport, reports);

        //Then
        assertEquals(1180, result);
    }

    @Test
    void testCalculateHealthyPeopleSusceptibleToInfection() {
        //Given
        Simulation simulation = new Simulation(
                1L,
                "TestName",
                10000,
                1000,
                2.0,
                2.0,
                2,
                1,
                100
        );
        Report report = Report.builder()
                .numberOfInfected(1160)
                .numberOfDeaths(20)
                .numberOfPeopleWhoAcquiredImmunity(0)
                .build();
        List<Report> reports = new ArrayList<>();

        //When
        double result = epidemicAlgorithm.calculateHealthyPeopleSusceptibleToInfection(simulation, report, reports);

        //Then
        assertEquals(8820, result);
    }

    @Test
    void testCalculateDeadPeople() {
        //Given
        Simulation simulation = new Simulation(
                1L,
                "TestName",
                10000,
                1000,
                2.0,
                2.0,
                2,
                1,
                100
        );
        Report firstReport = new Report(
                1L,
                0,
                1000,
                9000,
                0,
                0
        );

        Report secondReport = new Report(
                2L,
                1,
                1160,
                8820,
                0,
                0
        );
        List<Report> reports = new ArrayList<>();
        reports.add(firstReport);
        reports.add(secondReport);


        //When
        double result = epidemicAlgorithm.calculateDeadPeople(simulation, secondReport, reports);

        //Then
        assertEquals(20, result);
    }

    @Test
    void testCalculateRecoveredPeople() {
        //Given
        Simulation simulation = new Simulation(
                1L,
                "TestName",
                10000,
                1000,
                2.0,
                2.0,
                2,
                1,
                100
        );
        Report firstReport = new Report(
                1L,
                0,
                1000,
                9000,
                0,
                0
        );
        Report thirdReport = new Report(
                3L,
                2,
                1313,
                7664,
                23,
                0
        );

        List<Report> reports = new ArrayList<>();
        reports.add(firstReport);
        reports.add(new Report());
        reports.add(thirdReport);

        //When
        double result = epidemicAlgorithm.calculateRecoveredPeople(simulation, thirdReport, reports);

        //Then
        assertEquals(1000, result);
    }
}
