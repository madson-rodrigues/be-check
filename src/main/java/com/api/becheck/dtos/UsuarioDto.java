package com.api.becheck.dtos;

import javax.validation.constraints.NotBlank;

public class UsuarioDto {

    @NotBlank
    String nome;

    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotBlank
    String role;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
