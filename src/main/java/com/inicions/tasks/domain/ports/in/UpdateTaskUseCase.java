package com.inicions.tasks.domain.ports.in;

import com.inicions.tasks.domain.model.Task;

import java.util.Optional;

public interface UpdateTaskUseCase {
    Optional<Task> updateTask(Long id, Task updatedTask);
}
