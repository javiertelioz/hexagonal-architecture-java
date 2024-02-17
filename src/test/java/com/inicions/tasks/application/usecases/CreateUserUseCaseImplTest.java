package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.in.CreateUserUseCase;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateUserUseCaseImplTest {
    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createUserUseCase = new CreateUserUseCaseImpl(userRepositoryPort);
    }

    @Test
    void createUser_ShouldReturnUser() {
        User newUser = new User();
        User savedUser = new User();
        when(userRepositoryPort.save(newUser)).thenReturn(savedUser);

        User result = createUserUseCase.createUser(newUser);

        assertEquals(savedUser, result);
        verify(userRepositoryPort).save(newUser);
    }
}
