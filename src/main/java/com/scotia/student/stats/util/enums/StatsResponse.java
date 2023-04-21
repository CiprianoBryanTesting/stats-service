package com.scotia.student.stats.util.enums;

import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum StatsResponse {
    INVOKING_STUDENT_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "Error al invocar el servicio de Alumnos"),
    NO_STUDENTS(HttpStatus.OK, "No existen alumnos registrados"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Existe un error en el servicio de Estadísticas. Contactar con el administrador.");

    private final HttpStatus status;
    private final String message;
}
