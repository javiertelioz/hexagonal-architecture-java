package com.inicions.tasks.infrastructure.repositories;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.infrastructure.entities.TaskEntity;
import com.inicions.tasks.infrastructure.repositories.task.JpaTaskRepository;
import com.inicions.tasks.infrastructure.repositories.task.JpaTaskRepositoryAdapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JpaTaskRepositoryAdapterTest {
    @Mock
    private JpaTaskRepository jpaTaskRepository;

    private JpaTaskRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new JpaTaskRepositoryAdapter(jpaTaskRepository);
    }

    @Test
    void save_ShouldSaveTaskAndReturnDomainModel() {
        Task task = new Task(1L, "Test Task", "Description", LocalDateTime.of(LocalDate.now(), LocalTime.now()), true);
        TaskEntity taskEntity = TaskEntity.fromDomainModel(task);
        when(jpaTaskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

        Task savedTask = adapter.save(task);

        assertNotNull(savedTask);
        assertEquals(task.getId(), savedTask.getId());
        verify(jpaTaskRepository).save(any(TaskEntity.class));
    }

    @Test
    void update_WhenTaskExists_ShouldUpdateAndReturnTask() {
        Long taskId = 1L;
        Task originalTask = new Task(1L, "Original Task", "Original Description", LocalDateTime.of(LocalDate.now(), LocalTime.now()), true);
        Task updatedTask = new Task(1L, "Updated Task", "Updated Description", LocalDateTime.of(LocalDate.now(), LocalTime.now()), true);

        TaskEntity taskEntity = TaskEntity.fromDomainModel(originalTask);
        TaskEntity updatedTaskEntity = TaskEntity.fromDomainModel(updatedTask);

        when(jpaTaskRepository.existsById(taskId)).thenReturn(true);
        when(jpaTaskRepository.save(any(TaskEntity.class))).thenReturn(updatedTaskEntity);

        Optional<Task> result = adapter.update(updatedTask);

        assertTrue(result.isPresent());
        Task resultTask = result.get();
        assertEquals(updatedTask.getTitle(), resultTask.getTitle());
        assertEquals(updatedTask.getDescription(), resultTask.getDescription());
        assertEquals(updatedTask.isCompleted(), resultTask.isCompleted());

        verify(jpaTaskRepository).save(any(TaskEntity.class));
    }

    @Test
    void update_WhenTaskDoesNotExist_ShouldReturnEmptyOptional() {
        Long taskId = 2L;
        Task taskToUpdate = new Task(taskId, "Non-existent Task", "Does not exist", LocalDateTime.of(LocalDate.now(), LocalTime.now()), false);

        when(jpaTaskRepository.existsById(taskId)).thenReturn(false);

        Optional<Task> result = adapter.update(taskToUpdate);

        assertFalse(result.isPresent());
        verify(jpaTaskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    void findById_WhenExists_ShouldReturnTask() {
        Long taskId = 1L;
        Optional<TaskEntity> taskEntity = Optional.of(new TaskEntity()); // Configura según tu modelo
        when(jpaTaskRepository.findById(taskId)).thenReturn(taskEntity);

        Optional<Task> result = adapter.findById(taskId);

        assertTrue(result.isPresent());
        verify(jpaTaskRepository).findById(taskId);
    }

    @Test
    void findById_WhenNotExists_ShouldReturnEmpty() {
        Long taskId = 1L;
        when(jpaTaskRepository.findById(taskId)).thenReturn(Optional.empty());

        Optional<Task> result = adapter.findById(taskId);

        assertFalse(result.isPresent());
        verify(jpaTaskRepository).findById(taskId);
    }

    @Test
    void findAll_ShouldReturnAllTasks() {
        when(jpaTaskRepository.findAll()).thenReturn(Arrays.asList(new TaskEntity(), new TaskEntity())); // Ajusta según tu modelo

        List<Task> tasks = adapter.findAll();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        verify(jpaTaskRepository).findAll();
    }

    @Test
    void deleteById_WhenExists_ShouldReturnTrue() {
        Long taskId = 1L;
        when(jpaTaskRepository.existsById(taskId)).thenReturn(true);

        boolean result = adapter.deleteById(taskId);

        assertTrue(result);
        verify(jpaTaskRepository).deleteById(taskId);
    }

    @Test
    void deleteById_WhenNotExists_ShouldReturnFalse() {
        Long taskId = 1L;
        when(jpaTaskRepository.existsById(taskId)).thenReturn(false);

        boolean result = adapter.deleteById(taskId);

        assertFalse(result);
        verify(jpaTaskRepository, never()).deleteById(anyLong());
    }
}
