package com.api.becheck.dtos;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class UserHospitalIdsDto {
    @NotBlank
    UUID id_user;
    @NotBlank
    UUID id_hospital;

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public UUID getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(UUID id_hospital) {
        this.id_hospital = id_hospital;
    }
}
