package com.api.becheck.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PreNatalDto {


    @NotNull
    private boolean riscoObstetrico;

    private Long pesoAnterior;

    private Long altura;

    private String patologiaMaternoFraternal;

    private boolean dumConfiavel;

    public boolean isRiscoObstetrico() {
        return riscoObstetrico;
    }

    public void setRiscoObstetrico(boolean riscoObstetrico) {
        this.riscoObstetrico = riscoObstetrico;
    }

    public Long getPesoAnterior() {
        return pesoAnterior;
    }

    public void setPesoAnterior(Long pesoAnterior) {
        this.pesoAnterior = pesoAnterior;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }

    public String getPatologiaMaternoFraternal() {
        return patologiaMaternoFraternal;
    }

    public void setPatologiaMaternoFraternal(String patologiaMaternoFraternal) {
        this.patologiaMaternoFraternal = patologiaMaternoFraternal;
    }

    public boolean isDumConfiavel() {
        return dumConfiavel;
    }

    public void setDumConfiavel(boolean dumConfiavel) {
        this.dumConfiavel = dumConfiavel;
    }
}
