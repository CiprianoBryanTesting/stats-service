package com.whiz.clients.statsservice.controller;

import com.whiz.clients.statsservice.controller.dto.*;
import com.whiz.clients.statsservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kpi")
@CrossOrigin(origins = "*")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/clients")
    public ResponseEntity<StatsDTO> clientsKpi() {
        return ResponseEntity.ok(statsService.getClientsKpi());
    }
}
