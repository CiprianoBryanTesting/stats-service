package com.whiz.clients.statsservice.controller.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class StatsDTO {
    private Double averageAge;
    private Double standardDeviation;
}
