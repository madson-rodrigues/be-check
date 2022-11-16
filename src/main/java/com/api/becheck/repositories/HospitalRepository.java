package com.api.becheck.repositories;

import com.api.becheck.models.HospitalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalModel, String> {
    boolean existsByCnpj(String cnpj);

    Optional<HospitalModel> findById(UUID id);
}
