package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportGeneratorTestSuite {

    @Autowired
    private ReportGenerator reportGenerator;

    @Test
    void testGenerateReports() {
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

        //When
        reportGenerator.generateReports(simulation, reportGenerator.getReports());
        List<Report> resultList = reportGenerator.getReports();

        //Then
        assertEquals(100, resultList.size());

        //CleanUp
        resultList.clear();
    }
}
