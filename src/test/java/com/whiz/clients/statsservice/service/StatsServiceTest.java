package com.whiz.clients.statsservice.service;

import com.whiz.clients.statsservice.config.exception.*;
import com.whiz.clients.statsservice.controller.dto.*;
import com.whiz.clients.statsservice.proxy.*;
import com.whiz.clients.statsservice.service.impl.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {
    private StatsService statsService;

    @Mock
    private ClientProxy clientProxy;

    @BeforeEach
    void setUp() {
        statsService = new StatsServiceImpl(clientProxy);
    }

    @Test
    void getClientsKpiSuccessfully() {
        Mockito.when(clientProxy.getAll()).thenReturn(getClientsServiceGetResponse());

        StatsDTO clientsKpi = statsService.getClientsKpi();
        Assertions.assertEquals(6, clientsKpi.getAverageAge());
        Assertions.assertEquals(3.29, clientsKpi.getStandardDeviation());
    }

    @Test
    void getClientsKpi_ErrorInvokingClientService() {
        Mockito.when(clientProxy.getAll()).thenReturn(ResponseEntity.noContent().build());
        Assertions.assertThrows(BusinessException.class, () -> statsService.getClientsKpi());
    }

    @Test
    void getClientsKpi_NoClientsRegistered() {
        Mockito.when(clientProxy.getAll()).thenReturn(ResponseEntity.ok(new ArrayList<>()));
        Assertions.assertThrows(BusinessException.class, () -> statsService.getClientsKpi());
    }

    private ResponseEntity<List<ClientDTO>> getClientsServiceGetResponse() {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientDTOS.add(getClientDTOWithAge(2));
        clientDTOS.add(getClientDTOWithAge(3));
        clientDTOS.add(getClientDTOWithAge(6));
        clientDTOS.add(getClientDTOWithAge(8));
        clientDTOS.add(getClientDTOWithAge(11));
        return ResponseEntity.ok(clientDTOS);
    }

    private ClientDTO getClientDTOWithAge(int age) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setAge(age);
        return clientDTO;
    }
}