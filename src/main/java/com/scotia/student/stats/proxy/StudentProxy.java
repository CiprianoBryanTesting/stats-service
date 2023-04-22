package com.scotia.student.stats.proxy;

import com.scotia.student.stats.controller.dto.*;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.*;
import reactor.core.publisher.*;

import java.util.*;

@ReactiveFeignClient(name = "student-react-service", url="${proxy.student-service.url}")
public interface StudentProxy {
    @GetMapping("/student/all")
    Mono<List<StudentDTO>> getAll();
}
