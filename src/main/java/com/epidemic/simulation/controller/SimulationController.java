package com.epidemic.simulation.controller;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.dto.SimulationDto;
import com.epidemic.simulation.service.ReportGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/epidemic/")
@RequiredArgsConstructor
public class SimulationController {

    private final ReportGeneratorService reportGeneratorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createSimulation")
    public ResponseEntity<List<Report>> createSimulation(@RequestBody SimulationDto simulationDto) {
        return ResponseEntity.ok().body(reportGeneratorService.generateReports(simulationDto));
    }
}
