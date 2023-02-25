package com.whiz.clients.statsservice.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {
    private Integer id;
    private String names;
    private String lastname;
    private Integer age;
    private LocalDate birthdate;
    private LocalDate deathdate;
}
