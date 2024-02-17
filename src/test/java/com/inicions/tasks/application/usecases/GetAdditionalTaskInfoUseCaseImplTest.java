package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;
import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.GetAdditionalTaskInfoUseCase;
import com.inicions.tasks.domain.ports.out.ExternalServicePort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAdditionalTaskInfoUseCaseImplTest {
    @Mock
    private GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase;

    @Mock
    private ExternalServicePort externalServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAdditionalTaskInfoUseCase = new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }

    @Test
    void getAdditionalTaskInfoById_ShouldReturnTask() {
        Long taskId = 1L;
        AdditionalTaskInfo taskInfo = new AdditionalTaskInfo(
                1L,
                "joe",
                "joe_doe@email.com"
        );

        when(externalServicePort.getAdditionalTaskInfo(taskId)).thenReturn(taskInfo);
        AdditionalTaskInfo result = getAdditionalTaskInfoUseCase.getAdditionalTaskInfo(taskId);

        assertEquals(taskInfo, result);
        verify(externalServicePort).getAdditionalTaskInfo(taskId);
    }


}
