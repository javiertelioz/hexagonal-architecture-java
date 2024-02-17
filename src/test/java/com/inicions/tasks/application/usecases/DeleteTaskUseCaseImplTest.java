package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.ports.in.DeleteTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteTaskUseCaseImplTest {

    @Mock
    private DeleteTaskUseCase deleteTaskUseCase;

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteTaskUseCase = new DeleteTaskUseCaseImpl(taskRepositoryPort);
    }

    @Test
    void deleteTask_ShouldReturnTrue() {
        Long taskId = 1L;
        Boolean expected = true;

        when(taskRepositoryPort.deleteById(taskId)).thenReturn(expected);

        boolean result = deleteTaskUseCase.deleteTask(taskId);

        assertEquals(expected, result);
        verify(taskRepositoryPort).deleteById(taskId);
    }
}
