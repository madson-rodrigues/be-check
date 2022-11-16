package com.api.becheck.dtos;

import com.api.becheck.models.EnderecoModel;

import javax.validation.constraints.NotBlank;

public class HospitalDto {

    @NotBlank
    private String cnpj;

    @NotBlank
    private String nome;

    private EnderecoModel enderecoModel;

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
