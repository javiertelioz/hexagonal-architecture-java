package com.inicions.tasks.infrastructure.repositories.user;

import java.util.Optional;

import com.inicions.tasks.infrastructure.entities.RoleEntity;
import org.springframework.stereotype.Component;

import com.inicions.tasks.domain.model.Role;
import com.inicions.tasks.domain.ports.out.RoleRepositoryPort;

@Component
public class JpaRoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;

    public JpaRoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRoleRepository.findById(id).map(RoleEntity::toDomainModel);
    }
}
