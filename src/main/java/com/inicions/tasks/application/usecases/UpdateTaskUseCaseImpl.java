package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.UpdateTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;

import java.util.Optional;

public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public UpdateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepositoryPort.update(updatedTask);
    }
}
