package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.in.GetUserByEmailUseCase;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;

import java.util.Optional;

public class GetUserByEmailUseCaseImpl implements GetUserByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetUserByEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepositoryPort.findByEmail(email);
    }
}
