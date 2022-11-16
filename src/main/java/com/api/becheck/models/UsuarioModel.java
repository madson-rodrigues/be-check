package com.api.becheck.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="TB_USUARIOS")
@SQLDelete(sql = "UPDATE TB_USUARIOS SET deleted = true WHERE id_usuario=?")
@Where(clause = "deleted=false")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private UUID id;

    private boolean deleted = Boolean.FALSE;

    @NotNull
    String nome;

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_usuario")
    private List<PreNatalModel> PreNatalList;

    public List<PreNatalModel> getPreNatalList() {
        return PreNatalList;
    }

    public void setPreNatalList(List<PreNatalModel> preNatalList) {
        PreNatalList = preNatalList;
    }

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<HospitalModel> hospitais = new ArrayList<HospitalModel>();


    public List<HospitalModel> getHospitais() {
        return hospitais;
    }

    public void setHospitais(List<HospitalModel> hospitais) {
        this.hospitais = hospitais;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
