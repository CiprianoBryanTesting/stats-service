package com.scotia.student.stats.controller;

import com.scotia.student.stats.controller.dto.*;
import com.scotia.student.stats.service.*;
import com.scotia.student.stats.util.enums.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*")
@Slf4j
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/students")
    @CircuitBreaker(name = "students-stats", fallbackMethod = "apiFallBackStudentsStats")
    public ResponseEntity<StatsDTO> getStudentsAgeAverage() {
        return ResponseEntity.ok(statsService.getStudentsAgeAverage());
    }

    public ResponseEntity<?> apiFallBackStudentsStats(Exception exception) {
        log.info(StatsResponse.INTERNAL_SERVER_ERROR.getMessage() + ", Error={}", exception.getMessage());
        return ResponseEntity.noContent().build();
    }
}
