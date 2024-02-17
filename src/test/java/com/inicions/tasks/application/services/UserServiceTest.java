package com.inicions.tasks.application.services;


import com.inicions.tasks.domain.model.Task;
import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.in.CreateUserUseCase;
import com.inicions.tasks.domain.ports.in.GetUserByEmailUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private GetUserByEmailUseCase getUserByEmailUseCase;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(createUserUseCase, getUserByEmailUseCase);
    }

    @Test
    void createUser_ShouldCreateUserSuccessfully() {
        User newUser = new User();
        User savedUser = new User();
        when(createUserUseCase.createUser(newUser)).thenReturn(savedUser);

        User result = userService.createUser(newUser);

        assertEquals(savedUser, result);
        verify(createUserUseCase).createUser(newUser);
    }


    @Test
    void getUserByEmail_ShouldReturnTask() {
        String email = "joe@mail.com";

        Optional<User>  optionalUser = Optional.of(new User());
        when(getUserByEmailUseCase.getUserByEmail(email)).thenReturn(optionalUser);

        Optional<User> result = userService.getUserByEmail(email);

        assertEquals(optionalUser, result);
        verify(getUserByEmailUseCase).getUserByEmail(email);
    }
}
