package searchFreelancer.workfreela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.Mapper.ServicosMapper;
import searchFreelancer.workfreela.Services.ServicosService;
import searchFreelancer.workfreela.dto.ServicosDTO;
import searchFreelancer.workfreela.dto.ServicosResponseDTO;
import searchFreelancer.workfreela.exceptions.ServicoNotFoundException;
import searchFreelancer.workfreela.facade.FreelancerFacade;

import java.util.List;

@RestController
@RequestMapping(value = "/servicos")

public class ServicosController {

    @Autowired
    private FreelancerFacade freelancerFacade;
    @Autowired
    private ServicosMapper servicosMapper;


    @PostMapping
    public ResponseEntity<ServicosResponseDTO> toRecord(@RequestBody ServicosDTO servicosDTO) {
        Servicos servicos = servicosMapper.toEntity(servicosDTO);
        Servicos servicoRecorded = freelancerFacade.addServico(servicos);
        ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servicoRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicosResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ServicosResponseDTO>> searchAll() {
        List<Servicos> servicos = freelancerFacade.getAllServicos();
        List<ServicosResponseDTO> servicosResponseDTOS = servicosMapper.toDTO(servicos);
        return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchOne(@PathVariable(value = "id") Long id) {
        try {
            Servicos servico = freelancerFacade.getServicoById(id);
            ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servico);
            return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTO);
        } catch (ServicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicosResponseDTO> update(@PathVariable(value = "id") Long id, @RequestBody ServicosDTO servicosDTO) {
        try {
            Servicos servicos = servicosMapper.toEntity(servicosDTO);
            Servicos servicoUpdated = freelancerFacade.updateServico(id, servicos);
            ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servicoUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTO);
        } catch (ServicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        try {
            freelancerFacade.deleteServico(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ServicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
