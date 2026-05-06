package com.ucsal.cgaf.usuario.entity;

public enum Perfil {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String role;

    Perfil(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
