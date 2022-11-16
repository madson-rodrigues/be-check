package com.api.becheck.repositories;

import com.api.becheck.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    Optional<UsuarioModel> findUsuarioByUsername(String username);
}
