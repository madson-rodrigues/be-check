package com.api.becheck.controllers;

import com.api.becheck.dtos.HospitalDto;
import com.api.becheck.dtos.UserHospitalIdsDto;
import com.api.becheck.dtos.UsuarioDto;
import com.api.becheck.models.EnderecoModel;
import com.api.becheck.models.HospitalModel;
import com.api.becheck.models.UsuarioModel;
import com.api.becheck.services.EnderecoService;
import com.api.becheck.services.HospitalService;
import com.api.becheck.services.UsuarioService;
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
@RequestMapping("/hospital")
public class HospitalController {

    final HospitalService hospitalService;
    final EnderecoService enderecoService;
    final UsuarioService usuarioService;

    public HospitalController(HospitalService hospitalService, EnderecoService enderecoService, UsuarioService usuarioService) {
        this.hospitalService = hospitalService;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> saveHospital(@RequestBody @Valid HospitalDto hospitalDto){
        if(hospitalService.existsByCnpj(hospitalDto.getCnpj())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CNPJ is already in use!");
        }

        var hospitalmodel = new HospitalModel();
        var enderecoModel = new EnderecoModel();

        hospitalmodel.setNome(hospitalDto.getNome());
        hospitalmodel.setCnpj(hospitalDto.getCnpj());

        enderecoModel.setCep(hospitalDto.getEnderecoModel().getCep());
        enderecoModel.setCidade(hospitalDto.getEnderecoModel().getCidade());
        enderecoModel.setComplemento(hospitalDto.getEnderecoModel().getComplemento());
        enderecoModel.setNumero(hospitalDto.getEnderecoModel().getNumero());
        enderecoModel.setRua(hospitalDto.getEnderecoModel().getRua());

        hospitalmodel.setEnderecoModel(enderecoModel);

        enderecoService.save(enderecoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.save(hospitalmodel));
    }

   @PostMapping("/cadastrar-usuario")
    public ResponseEntity<Object> addUser(@RequestBody UserHospitalIdsDto ids){
        var id_hospital = ids.getId_hospital();
        var id_user = ids.getId_user();

        Optional<HospitalModel> hospitalModelOptional = hospitalService.findById(id_hospital);
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id_user);
        if(!hospitalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found ");
        }
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found ");
        }
        var hospitalModel = hospitalModelOptional.get();
        var usuarioModel = usuarioModelOptional.get();

        hospitalModel.getUsuarios().add(usuarioModel);


        return ResponseEntity.status(HttpStatus.OK).body( usuarioService.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<Page<HospitalModel>> getAllHospitais(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneHospital(@PathVariable(value = "id") UUID id){
        Optional<HospitalModel> hospitalModelOptional = hospitalService.findById(id);
        if(!hospitalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hospitalModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHospital(@PathVariable(value = "id") UUID id){
        Optional<HospitalModel> hospitalModelOptional = hospitalService.findById(id);
        if(!hospitalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found ");
        }
        hospitalService.delete(hospitalModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Hospital deleted sucessfully ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHospital(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid HospitalDto hospitalDto){
        Optional<HospitalModel> hospitalModelOptional = hospitalService.findById(id);
        if(!hospitalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found ");
        }
        var hospitalModel = hospitalModelOptional.get();
        hospitalModel.setCnpj(hospitalDto.getCnpj());
        hospitalModel.setNome(hospitalDto.getNome());
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.save(hospitalModel));
    }

}
