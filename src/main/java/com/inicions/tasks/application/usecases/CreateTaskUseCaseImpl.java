package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.ports.in.CreateTaskUseCase;
import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepositoryPort.save(task);
    }
}
