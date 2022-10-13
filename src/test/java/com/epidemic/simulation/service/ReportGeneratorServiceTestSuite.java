package com.epidemic.simulation.service;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.dto.SimulationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportGeneratorServiceTestSuite {

    @Autowired
    private ReportGeneratorService reportGeneratorService;

    @Test
    void testGenerateReports() {
        //Given
        SimulationDto simulationDto = new SimulationDto(
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
        reportGeneratorService.generateReports(simulationDto);
        List<Report> resultList = reportGeneratorService.getReports();

        //Then
        assertEquals(100, resultList.size());
    }
}
