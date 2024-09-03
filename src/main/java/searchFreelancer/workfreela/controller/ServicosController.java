package searchFreelancer.workfreela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.Mapper.ServicosMapper;
import searchFreelancer.workfreela.Services.ServicosService;
import searchFreelancer.workfreela.dto.ServicosDTO;
import searchFreelancer.workfreela.dto.ServicosResponseDTO;

@RestController
@RequestMapping(value = "/servicos")

public class ServicosController {

    @Autowired
    private ServicosService servicosService;
    @Autowired
    private ServicosMapper servicosMapper;

    @PostMapping
    public ResponseEntity<ServicosResponseDTO> toRecord (@RequestBody ServicosDTO servicosDTO){
        Servicos servicos =  servicosMapper.toEntity(servicosDTO);
        Servicos servicoRecorded = servicosService.toRecord(servicos);
        ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servicoRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicosResponseDTO);
    }

}
