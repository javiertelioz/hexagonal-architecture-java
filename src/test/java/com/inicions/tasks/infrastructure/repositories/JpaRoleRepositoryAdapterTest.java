package com.inicions.tasks.infrastructure.repositories;

import com.inicions.tasks.domain.model.Role;
import com.inicions.tasks.infrastructure.entities.RoleEntity;
import com.inicions.tasks.infrastructure.repositories.user.JpaRoleRepository;
import com.inicions.tasks.infrastructure.repositories.user.JpaRoleRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class JpaRoleRepositoryAdapterTest {
    @Mock
    private JpaRoleRepository jpaRoleRepository;

    @InjectMocks
    private JpaRoleRepositoryAdapter jpaRoleRepositoryAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findById_WhenRoleExists_ShouldReturnRole() {
        Long roleId = 1L;
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleId);
        roleEntity.setName(Role.ADMIN);

        when(jpaRoleRepository.findById(roleId)).thenReturn(Optional.of(roleEntity));

        Optional<Role> result = jpaRoleRepositoryAdapter.findById(roleId);

        assertTrue(result.isPresent());
        verify(jpaRoleRepository).findById(roleId);
    }

    @Test
    void findById_WhenRoleDoesNotExist_ShouldReturnEmpty() {
        Long roleId = 1L;
        when(jpaRoleRepository.findById(roleId)).thenReturn(Optional.empty());

        Optional<Role> result = jpaRoleRepositoryAdapter.findById(roleId);

        assertFalse(result.isPresent());
        verify(jpaRoleRepository).findById(roleId);
    }
}
