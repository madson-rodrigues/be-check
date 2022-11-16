package com.api.becheck.controllers;

import com.api.becheck.dtos.HospitalDto;
import com.api.becheck.dtos.PreNatalDto;
import com.api.becheck.dtos.UsuarioDto;
import com.api.becheck.models.PreNatalModel;
import com.api.becheck.models.UsuarioModel;
import com.api.becheck.services.PreNatalService;
import com.api.becheck.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pre-natal")
public class PreNatalController {

    final
    PreNatalService preNatalService;
    final UsuarioService usuarioService;

    public PreNatalController(PreNatalService preNatalService, UsuarioService usuarioService) {
        this.preNatalService = preNatalService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> savePreNatal(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid PreNatalDto preNatalDto){
        var preNatalModel = new PreNatalModel();
        var usuarioModelOptional = usuarioService.findById(id);

        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found ");
        }

        BeanUtils.copyProperties(preNatalDto, preNatalModel);

        usuarioModelOptional.get().getPreNatalList().add(preNatalModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModelOptional.get()));
    }

    @GetMapping
    public ResponseEntity<Page<PreNatalModel>> getAllPreNatais(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(preNatalService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePreNatal(@PathVariable(value = "id") UUID id){
        Optional<PreNatalModel> preNatalModelOptional = preNatalService.findById(id);
        if(!preNatalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pré-natal not found ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(preNatalModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePreNatal(@PathVariable(value = "id") UUID id){
        Optional<PreNatalModel> preNatalModelOptional = preNatalService.findById(id);
        if(!preNatalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pré-natal not found ");
        }
        preNatalService.delete(preNatalModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deleted sucessfully ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePreNatal(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid PreNatalDto preNatalDto){
        Optional<PreNatalModel> PreNatalModelOptional = preNatalService.findById(id);
        if(!PreNatalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pré-natal not found ");
        }
        var preNatalModel = PreNatalModelOptional.get();
        preNatalModel.setAltura(preNatalDto.getAltura());
        preNatalModel.setPatologiaMaternoFraternal(preNatalDto.getPatologiaMaternoFraternal());
        preNatalModel.setPesoAnterior(preNatalDto.getPesoAnterior());
        preNatalModel.setRiscoObstetrico(preNatalDto.isRiscoObstetrico());
        preNatalModel.setDumConfiavel(preNatalDto.isDumConfiavel());
        return ResponseEntity.status(HttpStatus.OK).body(preNatalService.save(preNatalModel));
    }
}
