package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.domain.Simulation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportFillerTest {

    @Autowired
    private ReportFiller reportFiller;

    @Autowired
    private ReportGenerator reportGenerator;

    @Test
    void shouldInsertDataToReport() {
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
        List<Report> reports = reportGenerator.getReports();

        //When
        reportGenerator.generateReports(simulation, reports);
        reportFiller.fillReportsWithData(simulation, reports);

        //Then
    }
}