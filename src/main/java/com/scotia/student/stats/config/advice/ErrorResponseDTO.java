package com.scotia.student.stats.config.advice;

import com.scotia.student.stats.config.exception.*;
import com.scotia.student.stats.util.constants.*;
import lombok.*;
import org.springframework.http.*;

@Data
public class ErrorResponseDTO {
    private HttpStatus httpStatus;
    private String message;
    private String date;

    public ErrorResponseDTO(BusinessException ex) {
        this.httpStatus = ex.getHttpStatus();
        this.message = ex.getMessage();
        this.date = ex.getDate().format(TimeConstants.DATE_TIME_FORMATTER);
    }
}
