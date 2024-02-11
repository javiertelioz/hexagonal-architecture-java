package com.inicions.tasks.domain.ports.in;

import com.inicions.tasks.domain.model.Task;

public interface CreateTaskUseCase {
    Task createTask(Task task);
}
