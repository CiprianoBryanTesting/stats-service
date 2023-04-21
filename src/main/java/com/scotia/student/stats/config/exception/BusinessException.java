package com.scotia.student.stats.config.exception;

import com.scotia.student.stats.util.enums.*;
import lombok.*;
import org.springframework.http.*;

import java.time.*;

@Data
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime date;

    public BusinessException(StatsResponse response) {
        this.httpStatus = response.getStatus();
        this.message = response.getMessage();
        this.date = LocalDateTime.now();
    }
}
