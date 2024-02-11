package com.inicions.tasks.application.services;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.in.*;

import java.util.Optional;

public class UserService implements CreateUserUseCase, GetUserByEmailUseCase {

    private final CreateUserUseCase createUserUseCase;

    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public UserService(
            CreateUserUseCase createUserUseCase,
            GetUserByEmailUseCase getUserByEmailUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    @Override
    public User createUser(User user) {
        return createUserUseCase.createUser(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return getUserByEmailUseCase.getUserByEmail(email);
    }
}
