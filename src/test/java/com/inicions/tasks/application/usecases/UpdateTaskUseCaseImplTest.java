package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.UpdateTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class UpdateTaskUseCaseImplTest {
    @Mock
    private UpdateTaskUseCase updateTaskUseCase;

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateTaskUseCase = new UpdateTaskUseCaseImpl(taskRepositoryPort);
    }

    @Test
    void updateTask_ShouldReturnTrue() {
        Long taskId = 1L;
        Task updateTask = new Task();
        Optional<Task> savedTask = Optional.of(new Task());

        when(taskRepositoryPort.update(updateTask)).thenReturn(savedTask);

        Optional<Task> result = updateTaskUseCase.updateTask(taskId, updateTask);

        assertEquals(savedTask, result);
        verify(taskRepositoryPort).update(updateTask);
    }
}
