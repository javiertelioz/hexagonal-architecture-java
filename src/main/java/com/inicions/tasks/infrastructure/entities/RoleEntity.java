package com.inicions.tasks.infrastructure.entities;

import com.inicions.tasks.domain.model.Role;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role name;

    public RoleEntity() {
    }

    // Constructor con todos los argumentos
    public RoleEntity(Long id, Role name) {
        this.id = id;
        this.name = name;
    }

    public Role toDomainModel() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }
}