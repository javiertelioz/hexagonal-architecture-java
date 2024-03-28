package com.inicions.tasks.infrastructure.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter) {
        return jpaTaskRepositoryAdapter;
    }

    // User Service
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserService userService(UserRepositoryPort userRepositoryPort) {
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new GetUserByEmailUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
        return jpaUserRepositoryAdapter;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ExternalServicePort externalServicePort(RestTemplate restTemplate) {
        return new ExternalServiceAdapter(restTemplate);
    }

}
