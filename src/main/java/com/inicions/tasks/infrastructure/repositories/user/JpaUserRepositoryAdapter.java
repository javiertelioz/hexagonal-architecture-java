package com.inicions.tasks.infrastructure.repositories.user;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.domain.ports.out.UserRepositoryPort;
import com.inicions.tasks.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.fromDomainModel(user);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return savedUserEntity.toDomainModel();
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaUserRepository.findById(id).map(UserEntity::toDomainModel);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(UserEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> update(User user) {
        if (jpaUserRepository.existsById(user.getId())) {
            UserEntity userEntity = UserEntity.fromDomainModel(user);
            UserEntity updatedUserEntity = jpaUserRepository.save(userEntity);
            return Optional.of(updatedUserEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        if (jpaUserRepository.existsById(id)) {
            jpaUserRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(UserEntity::toDomainModel);
    }
}