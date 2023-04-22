package com.scotia.student.stats.service.impl;

import com.scotia.student.stats.config.exception.*;
import com.scotia.student.stats.controller.dto.*;
import com.scotia.student.stats.proxy.*;
import com.scotia.student.stats.service.*;
import com.scotia.student.stats.util.*;
import com.scotia.student.stats.util.enums.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StudentProxy studentProxy;

    @Override
    public Mono<StatsDTO> getStudentsAgeAverage() {
        return studentProxy.getAll()
            .onErrorResume(Void -> Mono.error(new BusinessException(StatsResponse.INVOKING_STUDENT_ERROR)))
            .map(students -> {
                if (students == null) {
                    log.info(StatsResponse.INVOKING_STUDENT_ERROR.getMessage());
                    throw new BusinessException(StatsResponse.INVOKING_STUDENT_ERROR);
                }
                if (students.isEmpty()) {
                    log.info(StatsResponse.NO_STUDENTS.getMessage());
                    throw new BusinessException(StatsResponse.NO_STUDENTS);
                }

                int agesSum = students.stream().mapToInt(StudentDTO::getAge).sum();
                Double averageAge = 1. * agesSum / students.size();

                double standardDeviation = students.stream().mapToDouble(c -> Math.pow(c.getAge() - averageAge, 2)).sum();
                standardDeviation = Math.sqrt(standardDeviation/students.size());

                return new StatsDTO(NumberUtil.setDecimals(averageAge, 1), NumberUtil.setDecimals(standardDeviation, 2));
            });
    }
}
