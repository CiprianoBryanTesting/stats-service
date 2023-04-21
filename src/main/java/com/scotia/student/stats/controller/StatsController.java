package com.scotia.student.stats.controller;

import com.scotia.student.stats.controller.dto.*;
import com.scotia.student.stats.service.*;
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
    public ResponseEntity<StatsDTO> getStudentsAgeAverage() {
        return ResponseEntity.ok(statsService.getStudentsAgeAverage());
    }
}
