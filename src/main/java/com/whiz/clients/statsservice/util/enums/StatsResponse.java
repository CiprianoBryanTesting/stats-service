package com.whiz.clients.statsservice.util.enums;

import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum StatsResponse {
    INVOKING_CLIENT_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "Error al invocar el servicio de Clientes"),
    NO_CLIENTS(HttpStatus.OK, "No existen clientes registrados"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Existe un error en el servicio de Estadísticas. Contactar con el administrador.");

    private final HttpStatus status;
    private final String message;
}
