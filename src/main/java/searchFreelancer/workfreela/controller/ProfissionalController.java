package searchFreelancer.workfreela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Mapper.ProfissionalMapper;
import searchFreelancer.workfreela.Services.ProfissionalService;
import searchFreelancer.workfreela.dto.ProfissionalDTO;
import searchFreelancer.workfreela.dto.ProfissionalResponseDTO;
import searchFreelancer.workfreela.exceptions.ProfissionalNotFoundException;
import searchFreelancer.workfreela.facade.FreelancerFacade;


import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private FreelancerFacade freelancerFacade;
    @Autowired
    private ProfissionalMapper profissionalMapper;


    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> toRecord(@RequestBody ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapper.toEntity(profissionalDTO);
        Profissional profissionalRecorded = freelancerFacade.addProfissional(profissional);
        ProfissionalResponseDTO profissionalResponseDTO = profissionalMapper.toDTO(profissionalRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalResponseDTO>> searchAll() {
        List<Profissional> profissionais = freelancerFacade.getAllProfissionais();
        List<ProfissionalResponseDTO> profissionalResponseDTOS = profissionalMapper.toDTO(profissionais);
        return ResponseEntity.status(HttpStatus.OK).body(profissionalResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchOne(@PathVariable(value = "id") Long id) {
        try {
            Profissional profissional = freelancerFacade.getProfissionalById(id);
            ProfissionalResponseDTO profissionalResponseDTO = profissionalMapper.toDTO(profissional);
            return ResponseEntity.status(HttpStatus.OK).body(profissionalResponseDTO);
        } catch (ProfissionalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDTO> update(@PathVariable(value = "id") Long id, @RequestBody ProfissionalDTO profissionalDTO) {
        try {
            Profissional profissional = profissionalMapper.toEntity(profissionalDTO);
            Profissional profissionalUpdated = freelancerFacade.updateProfissional(id, profissional);
            ProfissionalResponseDTO profissionalResponseDTO = profissionalMapper.toDTO(profissionalUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(profissionalResponseDTO);
        } catch (ProfissionalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        try {
            freelancerFacade.deleteProfissional(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ProfissionalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
