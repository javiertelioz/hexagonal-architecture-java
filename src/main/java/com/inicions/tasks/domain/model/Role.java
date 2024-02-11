package com.inicions.tasks.domain.model;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    INVITED("invited");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
