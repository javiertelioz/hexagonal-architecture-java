package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.in.CreateUserUseCase;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }
}
