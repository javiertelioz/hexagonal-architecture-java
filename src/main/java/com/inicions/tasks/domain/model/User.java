package com.inicions.tasks.domain.model;

import com.inicions.tasks.infrastructure.entities.RoleEntity;

import java.time.LocalDateTime;
import java.util.Set;

public class User {
    private String id;

    private String firsName;

    private String lastName;

    private String email;

    private String phone;

    private String username;

    private String password;

    private Set<RoleEntity> roles;

    private LocalDateTime creationDate;

    public User() {}

    public User(String id, String firsName, String lastName, String email, String phone, String username, String password, Set<RoleEntity> roles, LocalDateTime creationDate) {
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
}
