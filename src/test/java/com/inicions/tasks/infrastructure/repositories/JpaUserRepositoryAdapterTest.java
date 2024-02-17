package com.inicions.tasks.infrastructure.repositories;

import com.inicions.tasks.domain.model.Role;
import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.infrastructure.entities.RoleEntity;
import com.inicions.tasks.infrastructure.entities.UserEntity;
import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepository;
import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JpaUserRepositoryAdapterTest {
    @Mock
    private JpaUserRepository jpaUserRepository;

    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveUserCorrectly() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        User user = new User(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                "testUser",
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        );

        UserEntity userEntity = UserEntity.fromDomainModel(user);
        when(jpaUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User savedUser = jpaUserRepositoryAdapter.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        verify(jpaUserRepository).save(any(UserEntity.class));
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUser() {
        String userId = "1";
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        Optional<UserEntity> userEntity = Optional.of(new UserEntity(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                "testUser",
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        ));
        when(jpaUserRepository.findById(userId)).thenReturn(userEntity);

        Optional<User> result = jpaUserRepositoryAdapter.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        verify(jpaUserRepository).findById(userId);
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldReturnEmpty() {
        String userId = "nonexistent";
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = jpaUserRepositoryAdapter.findById(userId);

        assertFalse(result.isPresent());
        verify(jpaUserRepository).findById(userId);
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));

        List<UserEntity> userEntities = Arrays.asList(
                new UserEntity("1",
                        "joe",
                        "doe",
                        "joe_doe@mail.com",
                        "55555555555",
                        "testUser",
                        "password",
                        roles,
                        LocalDateTime.of(LocalDate.now(), LocalTime.now())),
                new UserEntity("2",
                        "joe",
                        "doe",
                        "joe_doe@mail.com",
                        "55555555555",
                        "testUser",
                        "password",
                        roles,
                        LocalDateTime.of(LocalDate.now(), LocalTime.now())));
        when(jpaUserRepository.findAll()).thenReturn(userEntities);

        List<User> users = jpaUserRepositoryAdapter.findAll();

        assertEquals(2, users.size());
        verify(jpaUserRepository).findAll();
    }

    @Test
    void deleteById_WhenUserExists_ShouldReturnTrue() {
        String userId = "1";
        when(jpaUserRepository.existsById(userId)).thenReturn(true);

        boolean result = jpaUserRepositoryAdapter.deleteById(userId);

        assertTrue(result);
        verify(jpaUserRepository).deleteById(userId);
    }

    @Test
    void deleteById_WhenUserDoesNotExist_ShouldReturnFalse() {
        String userId = "nonexistent";
        when(jpaUserRepository.existsById(userId)).thenReturn(false);

        boolean result = jpaUserRepositoryAdapter.deleteById(userId);

        assertFalse(result);
        verify(jpaUserRepository).existsById(userId);
        verify(jpaUserRepository, never()).deleteById(userId);
    }

    @Test
    void update_WhenUserExists_ShouldUpdateAndReturnUser() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        User userToUpdate = new User(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                "testUser",
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        );
        UserEntity userEntity = UserEntity.fromDomainModel(userToUpdate);
        when(jpaUserRepository.existsById(userToUpdate.getId())).thenReturn(true);
        when(jpaUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        Optional<User> updatedUser = jpaUserRepositoryAdapter.update(userToUpdate);

        assertTrue(updatedUser.isPresent());
        assertEquals(userToUpdate.getUsername(), updatedUser.get().getUsername());
        verify(jpaUserRepository).save(any(UserEntity.class));
    }

    @Test
    void update_WhenUserDoesNotExist_ShouldReturnEmpty() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        User userToUpdate = new User(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                "testUser",
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        );
        when(jpaUserRepository.existsById(userToUpdate.getId())).thenReturn(false);

        Optional<User> updatedUser = jpaUserRepositoryAdapter.update(userToUpdate);

        assertFalse(updatedUser.isPresent());
        verify(jpaUserRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void findByUsername_WhenUserExists_ShouldReturnUser() {
        String username = "username";
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        Optional<UserEntity> userEntity = Optional.of(new UserEntity(
                "1",
                "joe",
                "doe",
                "joe_doe@mail.com",
                "55555555555",
                username,
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        ));
        when(jpaUserRepository.findByUsername(username)).thenReturn(userEntity);

        Optional<User> result = jpaUserRepositoryAdapter.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(jpaUserRepository).findByUsername(username);
    }

    @Test
    void findByUsername_WhenUserDoesNotExist_ShouldReturnEmpty() {
        String username = "nonexistent";
        when(jpaUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> result = jpaUserRepositoryAdapter.findByUsername(username);

        assertFalse(result.isPresent());
        verify(jpaUserRepository).findByUsername(username);
    }

    @Test
    void findByEmail_WhenUserExists_ShouldReturnUser() {
        String email = "test@test.com";
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(1L, Role.USER));
        Optional<UserEntity> userEntity = Optional.of(new UserEntity(
                "1",
                "joe",
                "doe",
                email,
                "55555555555",
                "joeDoe",
                "password",
                roles,
                LocalDateTime.of(LocalDate.now(), LocalTime.now())
        ));
        when(jpaUserRepository.findByEmail(email)).thenReturn(userEntity);

        Optional<User> result = jpaUserRepositoryAdapter.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(jpaUserRepository).findByEmail(email);
    }

    @Test
    void findByEmail_WhenUserDoesNotExist_ShouldReturnEmpty() {
        String email = "nonexistent@test.com";
        when(jpaUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> result = jpaUserRepositoryAdapter.findByEmail(email);

        assertFalse(result.isPresent());
        verify(jpaUserRepository).findByEmail(email);
    }
}
