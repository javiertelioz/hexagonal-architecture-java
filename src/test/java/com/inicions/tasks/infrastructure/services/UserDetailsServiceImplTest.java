package com.inicions.tasks.infrastructure.services;

import com.inicions.tasks.domain.model.Role;
import com.inicions.tasks.infrastructure.entities.RoleEntity;
import com.inicions.tasks.infrastructure.entities.UserEntity;
import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {
    @Mock
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsService = new UserDetailsServiceImpl(jpaUserRepositoryAdapter);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        String username = "testUser";
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        UserEntity userEntity = new UserEntity(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                username,
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        );

        when(jpaUserRepositoryAdapter.findByUsername(username)).thenReturn(Optional.of(userEntity.toDomainModel()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        String username = "nonExistentUser";

        when(jpaUserRepositoryAdapter.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}
