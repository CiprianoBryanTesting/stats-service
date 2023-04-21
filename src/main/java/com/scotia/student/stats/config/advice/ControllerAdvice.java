package com.scotia.student.stats.config.advice;

import com.scotia.student.stats.config.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(BusinessException exception) {
        return new ResponseEntity<>(new ErrorResponseDTO(exception), exception.getHttpStatus());
    }
}
