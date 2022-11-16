package com.api.becheck.controllers;

import com.api.becheck.dtos.UsuarioDto;
import com.api.becheck.models.UsuarioModel;
import com.api.becheck.services.HospitalService;
import com.api.becheck.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;
    final HospitalService hospitalService;

    public UsuarioController(UsuarioService usuarioService, HospitalService hospitalService) {
        this.usuarioService = usuarioService;
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioModel>> getAllUsuarios(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found ");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deleted sucessfully ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHospital(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> UsuarioModelOptional = usuarioService.findById(id);
        if(!UsuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found ");
        }
        var usuarioModel = UsuarioModelOptional.get();
        usuarioModel.setUsername(usuarioDto.getUsername());
        usuarioModel.setNome(usuarioDto.getNome());
        usuarioModel.setRole(usuarioDto.getRole());
        usuarioModel.setPassword(usuarioDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

}
