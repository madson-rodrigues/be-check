package com.api.becheck.repositories;

import com.api.becheck.models.PreNatalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreNatalRepository extends JpaRepository<PreNatalModel, UUID> {
}
