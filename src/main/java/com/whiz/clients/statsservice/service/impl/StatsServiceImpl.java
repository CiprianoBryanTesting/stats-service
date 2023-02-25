package com.whiz.clients.statsservice.service.impl;

import com.whiz.clients.statsservice.controller.dto.*;
import com.whiz.clients.statsservice.proxy.*;
import com.whiz.clients.statsservice.service.*;
import com.whiz.clients.statsservice.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private ClientProxy clientProxy;

    @Override
    public StatsDTO getClientsKpi() {
        List<ClientDTO> clients = clientProxy.getAll().getBody();
        int agesSum = clients.stream().mapToInt(ClientDTO::getAge).sum();
        Double averageAge = 1. * agesSum / clients.size();

        double standardDeviation = clients.stream().mapToDouble(c -> Math.pow(c.getAge() - averageAge, 2)).sum();
        standardDeviation = Math.sqrt(standardDeviation/clients.size());

        return new StatsDTO(NumberUtil.setDecimals(averageAge, 1), NumberUtil.setDecimals(standardDeviation, 2));
    }
}
