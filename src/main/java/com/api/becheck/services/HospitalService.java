package com.api.becheck.services;

import com.api.becheck.models.HospitalModel;
import com.api.becheck.repositories.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalService {

    final
    HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public HospitalModel save(HospitalModel hospitalmodel) {
        return hospitalRepository.save(hospitalmodel);
    }

    public boolean existsByCnpj(String cnpj) {
        return hospitalRepository.existsByCnpj(cnpj);
    }

    public Page<HospitalModel> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    public List<HospitalModel> findAll() {
        return hospitalRepository.findAll();
    }
    public Optional<HospitalModel> findById(UUID id) {
        return hospitalRepository.findById(id);
    }

    @Transactional
    public void delete(HospitalModel hospitalModel) {
        hospitalRepository.delete(hospitalModel);
    }


}
