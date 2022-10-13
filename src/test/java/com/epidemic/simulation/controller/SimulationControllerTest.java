package com.epidemic.simulation.controller;

import com.epidemic.simulation.domain.Report;
import com.epidemic.simulation.dto.SimulationDto;
import com.epidemic.simulation.service.ReportGeneratorService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(SimulationController.class)
class SimulationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportGeneratorService reportGeneratorService;

    @Test
    void shouldCreateSimulationWithReports() throws Exception {
        //Given
        SimulationDto simulationDto = new SimulationDto(1L, "Test", 10000, 1000,
                2.0, 2.0, 2, 1, 10);
        List<Report> createdList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            createdList.add(Report.builder().id(i+1L).day(i).build());
        }

        when(reportGeneratorService.generateReports(any(SimulationDto.class))).thenReturn(createdList);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(simulationDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/epidemic/createSimulation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].day", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].day", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].id", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[9].day", Matchers.is(9)));
    }
}
