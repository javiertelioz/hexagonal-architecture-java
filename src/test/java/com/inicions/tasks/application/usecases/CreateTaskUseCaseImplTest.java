package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.CreateTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateTaskUseCaseImplTest {

    @Mock
    private CreateTaskUseCase createTaskUseCase;

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createTaskUseCase = new CreateTaskUseCaseImpl(taskRepositoryPort);
    }

    @Test
    void createTask_ShouldReturnUser() {
        Task newTask = new Task();
        Task savedTask = new Task();

        when(taskRepositoryPort.save(newTask)).thenReturn(savedTask);

        Task result = createTaskUseCase.createTask(newTask);

        assertEquals(savedTask, result);
        verify(taskRepositoryPort).save(newTask);
    }
}
