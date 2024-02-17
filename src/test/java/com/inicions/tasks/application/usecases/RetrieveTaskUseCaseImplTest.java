package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.RetrieveTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RetrieveTaskUseCaseImplTest {
    @Mock
    private RetrieveTaskUseCase retrieveTaskUseCase;

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        retrieveTaskUseCase = new RetrieveTaskUseCaseImpl(taskRepositoryPort);
    }

    @Test
    void getTaskById_ShouldReturnTrue() {
        Long taskId = 1L;

        Optional<Task> savedTask = Optional.of(new Task());

        when(taskRepositoryPort.findById(taskId)).thenReturn(savedTask);

        Optional<Task> result = retrieveTaskUseCase.getTaskById(taskId);

        assertEquals(savedTask, result);
        verify(taskRepositoryPort).findById(taskId);
    }

    @Test
    void retrieveTask_ShouldReturnTrue() {
        Long taskId = 1L;

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskRepositoryPort.findAll()).thenReturn(tasks);

        List<Task> result = retrieveTaskUseCase.getAllTasks();

        assertEquals(3, result.size());
        verify(taskRepositoryPort).findAll();
    }
}
