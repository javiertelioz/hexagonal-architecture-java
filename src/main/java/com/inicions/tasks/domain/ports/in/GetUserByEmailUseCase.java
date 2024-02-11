package com.inicions.tasks.domain.ports.in;

import com.inicions.tasks.domain.model.User;

import java.util.Optional;

public interface GetUserByEmailUseCase {
    Optional<User> getUserByEmail(String email);
}
