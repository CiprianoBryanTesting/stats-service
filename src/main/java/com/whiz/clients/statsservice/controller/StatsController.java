package com.whiz.clients.statsservice.controller;

import com.whiz.clients.statsservice.controller.dto.*;
import com.whiz.clients.statsservice.service.*;
import com.whiz.clients.statsservice.util.enums.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kpi")
@CrossOrigin(origins = "*")
@Slf4j
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/clients")
    @CircuitBreaker(name = "clients-kpi", fallbackMethod = "apiFallBackClientsKpi")
    public ResponseEntity<StatsDTO> clientsKpi() {
        return ResponseEntity.ok(statsService.getClientsKpi());
    }

    public ResponseEntity<?> apiFallBackClientsKpi(Exception exception) {
        log.info(StatsResponse.INTERNAL_SERVER_ERROR.getMessage() + ", Error={}", exception.getMessage());
        return ResponseEntity.noContent().build();
    }
}
