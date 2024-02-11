package com.inicions.tasks.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inicions.tasks.application.services.UserService;
import com.inicions.tasks.application.services.TaskService;

import com.inicions.tasks.application.usecases.*;
import com.inicions.tasks.domain.ports.in.GetAdditionalTaskInfoUseCase;

import com.inicions.tasks.domain.ports.out.TaskRepositoryPort;
import com.inicions.tasks.domain.ports.out.ExternalServicePort;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;

import com.inicions.tasks.infrastructure.adapters.ExternalServiceAdapter;

import com.inicions.tasks.infrastructure.repositories.task.JpaTaskRepositoryAdapter;
import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepositoryAdapter;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepositoryPort, GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase) {
        return new TaskService(
                new CreateTaskUseCaseImpl(taskRepositoryPort),
                new RetrieveTaskUseCaseImpl(taskRepositoryPort),
                new UpdateTaskUseCaseImpl(taskRepositoryPort),
                new DeleteTaskUseCaseImpl(taskRepositoryPort),
                getAdditionalTaskInfoUseCase
        );
    }

    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter) {
        return jpaTaskRepositoryAdapter;
    }

    // User Service
    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort) {
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new GetUserByEmailUseCaseImpl(userRepositoryPort)
            );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
        return jpaUserRepositoryAdapter;
    }

    @Bean
    public GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }

    @Bean
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }


}
