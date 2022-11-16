package com.api.becheck.services;

import com.api.becheck.models.EnderecoModel;
import com.api.becheck.models.HospitalModel;
import com.api.becheck.repositories.EnderecoRepository;
import com.api.becheck.repositories.HospitalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EnderecoService {
    EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoModel save(EnderecoModel enderecomodel) {
        return enderecoRepository.save(enderecomodel);
    }
}
