package com.whiz.clients.statsservice.service.impl;

import com.whiz.clients.statsservice.config.exception.*;
import com.whiz.clients.statsservice.controller.dto.*;
import com.whiz.clients.statsservice.proxy.*;
import com.whiz.clients.statsservice.service.*;
import com.whiz.clients.statsservice.util.*;
import com.whiz.clients.statsservice.util.enums.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final ClientProxy clientProxy;

    @Override
    public StatsDTO getClientsKpi() {
        List<ClientDTO> clients = clientProxy.getAll().getBody();
        if (clients == null) {
            log.info(StatsResponse.INVOKING_CLIENT_ERROR.getMessage());
            throw new BusinessException(StatsResponse.INVOKING_CLIENT_ERROR);
        }
        if (clients.isEmpty()) {
            log.info(StatsResponse.NO_CLIENTS.getMessage());
            throw new BusinessException(StatsResponse.NO_CLIENTS);
        }

        int agesSum = clients.stream().mapToInt(ClientDTO::getAge).sum();
        Double averageAge = 1. * agesSum / clients.size();

        double standardDeviation = clients.stream().mapToDouble(c -> Math.pow(c.getAge() - averageAge, 2)).sum();
        standardDeviation = Math.sqrt(standardDeviation/clients.size());

        return new StatsDTO(NumberUtil.setDecimals(averageAge, 1), NumberUtil.setDecimals(standardDeviation, 2));
    }
}
