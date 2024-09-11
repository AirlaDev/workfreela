package searchFreelancer.workfreela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.Mapper.DetailServiceMapper;
import searchFreelancer.workfreela.Services.DetailService;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.dto.DetailServiceDTO;
import searchFreelancer.workfreela.exceptions.DetalhesNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/detalhes")
public class DetailServiceController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private DetailServiceMapper detailServiceMapper;

    @PostMapping
    public ResponseEntity<DetailResponseDTO> toRecord(@RequestBody DetailServiceDTO detailServiceDTO) {
        DetalheServico detalheServico = detailServiceMapper.toEntity(detailServiceDTO);
        DetalheServico detalheServicoRecorded = detailService.toRecord(detalheServico);
        DetailResponseDTO detailResponseDTO = detailServiceMapper.toDTO(detalheServicoRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(detailResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DetailResponseDTO>> searchAll() {
        List<DetalheServico> detalhes = detailService.searchAll();
        List<DetailResponseDTO> detailResponseDTOS = detailServiceMapper.toDTO(detalhes);
        return ResponseEntity.status(HttpStatus.OK).body(detailResponseDTOS);
    }

    @GetMapping("/profissional/{profissionalId}/servico/{servicoId}")
    public ResponseEntity<Object> searchOne(
            @PathVariable(value = "profissionalId") Long profissionalId,
            @PathVariable(value = "servicoId") Long servicoId) {
        try {
            DetalheServico_PK detalheServicoPK = new DetalheServico_PK();
            detalheServicoPK.setId_profissional(new Profissional(profissionalId));
            detalheServicoPK.setId_servicos(new Servicos(servicoId));

            DetalheServico detalheServicoRecorded = detailService.searchDetalheById(detalheServicoPK);
            DetailResponseDTO detailResponseDTO = detailServiceMapper.toDTO(detalheServicoRecorded);

            return ResponseEntity.status(HttpStatus.OK).body(detailResponseDTO);
        } catch (DetalhesNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
