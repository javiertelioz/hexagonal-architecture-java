package com.inicions.tasks.infrastructure.repositories.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inicions.tasks.infrastructure.entities.RoleEntity;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
}
