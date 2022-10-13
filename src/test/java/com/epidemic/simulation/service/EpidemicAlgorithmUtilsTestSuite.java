package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import com.epidemic.simulation.utils.EpidemicAlgorithmUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EpidemicAlgorithmUtilsTestSuite {

    @Autowired
    private EpidemicAlgorithmUtils epidemicAlgorithmUtils;
    private final Simulation sim = new Simulation(
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

    @Test
    void shouldCalculateInfectedPeople() {
        //Given
        int day = 1;
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L, 0, 1000.0, 9000.0, 0.0, 0.0));
        reports.add(new Report(2L, 1, 0.0, 8820.0, 0.0, 0.0));

        //When
        double result = epidemicAlgorithmUtils.calculateInfectedPeople(sim, day, reports);

        //Then
        assertEquals(1180.0, result);
    }

    @Test
    void shouldCalculateHealthyPeopleSusceptibleToInfection() {
        //Given
        int day = 1;
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L, 0, 1000.0, 9000.0, 0.0, 0.0));
        reports.add(new Report(2L, 1, 1180.0, 0.0, 0.0, 0.0));

        //When
        double result = epidemicAlgorithmUtils.calculateHealthyPeopleSusceptibleToInfection(sim, day, reports);

        //Then
        assertEquals(8820.0, result);
    }

    @Test
    void shouldCalculateDeadPeople() {
        //Given
        int day = 2;
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L, 0, 1000.0, 9000.0, 0.0, 0.0));
        reports.add(new Report(2L, 1, 1180.0, 0.0, 0.0, 0.0));
        reports.add(new Report(3L, 2, 1356.4, 7620.0, 0.0, 0.0));

        //When
        double result = epidemicAlgorithmUtils.calculateDeadPeople(sim, day, reports);

        //Then
        assertEquals(23.6, result);
    }

    @Test
    void shouldCalculateRecoveredPeople() {
        //Given
        int day = 2;
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L, 0, 1000.0, 9000.0, 0.0, 0.0));
        reports.add(new Report(2L, 1, 1180.0, 0.0, 0.0, 0.0));
        reports.add(new Report(3L, 2, 1356.4, 7620.0, 0.0, 0.0));

        //When
        double result = epidemicAlgorithmUtils.calculateRecoveredPeople(sim, day, reports);

        //Then
        assertEquals(1000, result);
    }
}
