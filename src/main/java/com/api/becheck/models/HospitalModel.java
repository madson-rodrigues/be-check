package com.api.becheck.models;


import com.api.becheck.repositories.UsuarioRepository;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
@Entity
@Table(name="TB_HOSPITAL")
@SQLDelete(sql = "UPDATE TB_HOSPITAL SET deleted = true WHERE id_hospital=?")
@Where(clause = "deleted=false")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class HospitalModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hospital")
    private UUID id;
    @NotNull
    private String cnpj;

    @NotNull
    private String nome;

    private boolean deleted = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JoinColumn(name="endereco_id")
    private EnderecoModel enderecoModel;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "hospital_usuario", joinColumns = { @JoinColumn(name = "hospital_id", referencedColumnName = "id_hospital") },
    inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
    private List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();

    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EnderecoModel getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(EnderecoModel enderecoModel) {
        this.enderecoModel = enderecoModel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
