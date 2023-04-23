package com.scotia.student.stats.controller;

import com.scotia.student.stats.controller.dto.*;
import com.scotia.student.stats.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.reactive.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.reactive.server.*;
import reactor.core.publisher.*;

@WebFluxTest(controllers = StatsController.class)
@AutoConfigureWebTestClient
class StatsControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private StatsService statsService;

    private final String STUDENTS_STATS_URL = "/stats/students";

    @Test
    void successfulGetStudentsStats() {
        StatsDTO statsDTO = new StatsDTO(24.7, 4.11);

        Mockito.when(statsService.getStudentsAgeAverage()).thenReturn(Mono.just(statsDTO));

        webTestClient
                .get()
                .uri(STUDENTS_STATS_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(StatsDTO.class)
                .consumeWith(response -> {
                    StatsDTO stats = response.getResponseBody();
                    Assertions.assertEquals(24.7, stats.getAverageAge());
                });
    }
}