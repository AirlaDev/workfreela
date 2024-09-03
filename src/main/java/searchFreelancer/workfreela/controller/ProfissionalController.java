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

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    @PostMapping("/toRecord")
    public ResponseEntity<ProfissionalResponseDTO> toRecord(@RequestBody ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapper.toEntity(profissionalDTO);
        Profissional profissional1Recorded = profissionalService.toRecord(profissional);
        ProfissionalResponseDTO profissionalResponseDTO = profissionalMapper.toDTO(profissional1Recorded);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profissionalResponseDTO);
    }
}
