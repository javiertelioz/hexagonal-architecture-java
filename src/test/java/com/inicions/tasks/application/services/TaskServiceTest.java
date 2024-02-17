package com.inicions.tasks.application.services;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;
import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    private RetrieveTaskUseCase retrieveTaskUseCase;

    @Mock
    private CreateTaskUseCase createTaskUseCase;

    @Mock
    private UpdateTaskUseCase updateTaskUseCase;

    @Mock
    private DeleteTaskUseCase deleteTaskUseCase;

    @Mock
    private GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(createTaskUseCase, retrieveTaskUseCase, updateTaskUseCase, deleteTaskUseCase, getAdditionalTaskInfoUseCase);
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Task());

        when(retrieveTaskUseCase.getAllTasks()).thenReturn(expectedTasks);

        List<Task> actualTasks = taskService.getAllTasks();

        assertEquals(expectedTasks, actualTasks);
        verify(retrieveTaskUseCase).getAllTasks();
    }

    @Test
    void createTask_ShouldCreateTaskSuccessfully() {
        Task newTask = new Task();
        Task savedTask = new Task();
        when(createTaskUseCase.createTask(newTask)).thenReturn(savedTask);

        Task result = taskService.createTask(newTask);

        assertEquals(savedTask, result);
        verify(createTaskUseCase).createTask(newTask);
    }

    @Test
    void getTaskById_ShouldReturnTask() {
        Long taskId = 1L;
        Optional<Task> optionalTask = Optional.of(new Task());
        when(retrieveTaskUseCase.getTaskById(taskId)).thenReturn(optionalTask);

        Optional<Task> result = taskService.getTaskById(taskId);

        assertEquals(optionalTask, result);
        verify(retrieveTaskUseCase).getTaskById(taskId);
    }

    @Test
    void updateTask_ShouldUpdateTaskSuccessfully() {
        Long taskId = 1L;
        Task updatedTask = new Task(); // Configura el objeto según tu modelo
        Optional<Task> optionalUpdatedTask = Optional.of(updatedTask);
        when(updateTaskUseCase.updateTask(taskId, updatedTask)).thenReturn(optionalUpdatedTask);

        Optional<Task> result = taskService.updateTask(taskId, updatedTask);

        assertEquals(optionalUpdatedTask, result);
        verify(updateTaskUseCase).updateTask(taskId, updatedTask);
    }

    @Test
    void deleteTask_ShouldDeleteTaskSuccessfully() {
        Long taskId = 1L;
        when(deleteTaskUseCase.deleteTask(taskId)).thenReturn(true);

        boolean result = taskService.deleteTask(taskId);

        assertTrue(result);
        verify(deleteTaskUseCase).deleteTask(taskId);
    }

    @Test
    void getAdditionalTaskInfo_ShouldReturnAdditionalInfo() {
        Long taskId = 1L;
        AdditionalTaskInfo additionalTaskInfo = new AdditionalTaskInfo(1L, "joe", "joe@mail.com");
        when(getAdditionalTaskInfoUseCase.getAdditionalTaskInfo(taskId)).thenReturn(additionalTaskInfo);

        AdditionalTaskInfo result = taskService.getAdditionalTaskInfo(taskId);

        assertEquals(additionalTaskInfo, result);
        verify(getAdditionalTaskInfoUseCase).getAdditionalTaskInfo(taskId);
    }

}