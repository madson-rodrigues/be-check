package com.api.becheck.services;

import com.api.becheck.models.PreNatalModel;
import com.api.becheck.repositories.PreNatalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PreNatalService {

    final
    PreNatalRepository preNatalRepository;

    public PreNatalService(PreNatalRepository preNatalRepository) {
        this.preNatalRepository = preNatalRepository;
    }

    public PreNatalModel save(PreNatalModel preNatalModel) {
        return preNatalRepository.save(preNatalModel);
    }


    public Page<PreNatalModel> findAll(Pageable pageable) {
        return preNatalRepository.findAll(pageable);
    }

    public Optional<PreNatalModel> findById(UUID id) {
        return preNatalRepository.findById(id);
    }

    public void delete(PreNatalModel preNatalModel) {
        preNatalRepository.delete(preNatalModel);
    }
}
