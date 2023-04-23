package com.scotia.student.stats.service.impl;

import com.scotia.student.stats.config.exception.*;
import com.scotia.student.stats.controller.dto.*;
import com.scotia.student.stats.proxy.*;
import com.scotia.student.stats.service.*;
import com.scotia.student.stats.util.enums.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.beans.factory.annotation.*;
import reactor.core.publisher.*;
import reactor.test.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class StatsServiceImplTest {
    @Autowired
    private StatsService statsService;
    @Mock
    private StudentProxy studentProxy;

    @BeforeEach
    void setUp() {
        statsService = new StatsServiceImpl(studentProxy);
    }

    @Test
    void successfulGetStudentsAgeAverage() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(getStudentDTO(1, 12));
        studentDTOList.add(getStudentDTO(2, 13));
        studentDTOList.add(getStudentDTO(3, 14));
        studentDTOList.add(getStudentDTO(4, 15));

        StatsDTO statsDTO = new StatsDTO(13.5, 1.12);

        Mockito.when(studentProxy.getAll()).thenReturn(Mono.just(studentDTOList));

        Mono<StatsDTO> statsExpected = statsService.getStudentsAgeAverage();
        StepVerifier.create(statsExpected).expectNext(statsDTO).verifyComplete();
    }

    @Test
    void failureGetStudentsAgeAverage() {
        Mockito.when(studentProxy.getAll()).thenReturn(Mono.error(new BusinessException(StatsResponse.INTERNAL_SERVER_ERROR)));

        Mono<StatsDTO> statsExpected = statsService.getStudentsAgeAverage();
        StepVerifier.create(statsExpected).expectError(BusinessException.class);
    }

    private StudentDTO getStudentDTO(Integer id, Integer age) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        studentDTO.setName("Name");
        studentDTO.setLastname("Lastname");
        studentDTO.setStatus("activo");
        studentDTO.setAge(age);
        return studentDTO;
    }
}