package com.scotia.student.stats.service;

import com.scotia.student.stats.controller.dto.*;
import reactor.core.publisher.*;

public interface StatsService {
    Mono<StatsDTO> getStudentsAgeAverage();
}
