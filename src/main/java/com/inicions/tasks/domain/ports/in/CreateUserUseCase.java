package com.inicions.tasks.domain.ports.in;

import com.inicions.tasks.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
