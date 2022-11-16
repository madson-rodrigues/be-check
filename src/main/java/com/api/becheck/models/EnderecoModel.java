package com.api.becheck.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="enderecos")
public class EnderecoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_endereco")
    private UUID id;

    @NotNull
    private String rua;

    @NotNull
    private String cidade;

    @NotNull
    private String numero;

    @NotNull
    private String cep;

    private String complemento;

    @OneToOne(mappedBy = "enderecoModel")
    private HospitalModel hospitalModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public HospitalModel getHospitalModel() {
        return hospitalModel;
    }

    public void setHospitalModel(HospitalModel hospitalModel) {
        this.hospitalModel = hospitalModel;
    }






}
