package com.api.becheck.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="TB_PRE_NATAL")
@SQLDelete(sql = "UPDATE TB_PRE_NATAL SET deleted = true WHERE id_pre_natal=?")
@Where(clause = "deleted=false")
public class PreNatalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pre_natal")
    private UUID id;

    private boolean deleted = Boolean.FALSE;

    @NotNull
    private boolean riscoObstetrico;

    @NotNull
    private Long pesoAnterior;

    @NotNull
    private Long altura;

    private String patologiaMaternoFraternal;

    @NotNull
    private boolean dumConfiavel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

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
