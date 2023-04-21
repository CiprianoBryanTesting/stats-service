package com.scotia.student.stats.proxy;

import com.scotia.student.stats.controller.dto.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "student-service", url="${proxy.student-service.url}")
public interface StudentProxy {
    @GetMapping("/student/all")
    ResponseEntity<List<StudentDTO>> getAll();
}
