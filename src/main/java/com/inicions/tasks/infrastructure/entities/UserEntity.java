package com.inicions.tasks.infrastructure.entities;

import com.inicions.tasks.domain.model.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String firsName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    private String password;

    private LocalDateTime creationDate;

    public UserEntity() {}

    public UserEntity(
            String id,
            String firsName,
            String lastName,
            String email,
            String phone,
            String username,
            String password,
            Set<RoleEntity> roles,
            LocalDateTime creationDate
    ) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.creationDate = creationDate;
    }

    public static UserEntity fromDomainModel(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirsName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles(),
                user.getCreationDate()
        );
    }

    public User toDomainModel() {
        return new User(
                id,
                firsName,
                lastName,
                email,
                phone,
                username,
                password,
                roles,
                creationDate
        );
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;
}
