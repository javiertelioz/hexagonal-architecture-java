package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserByEmailUseCaseImplTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;

    private GetUserByEmailUseCaseImpl getUserByEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserByEmailUseCase = new GetUserByEmailUseCaseImpl(userRepositoryPort);
    }

    @Test
    void getUserByEmail_ShouldReturnUser() {
        String email = "test@example.com";
        Optional<User> expectedUser = Optional.of(new User());
        when(userRepositoryPort.findByEmail(email)).thenReturn(expectedUser);

        Optional<User> result = getUserByEmailUseCase.getUserByEmail(email);

        assertEquals(expectedUser, result);
        verify(userRepositoryPort).findByEmail(email);
    }
}
