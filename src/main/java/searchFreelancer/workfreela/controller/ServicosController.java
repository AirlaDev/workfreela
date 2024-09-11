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

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ServicosResponseDTO>> searchAll(){
        List<Servicos> servicos = servicosService.searchAll();
        List<ServicosResponseDTO> servicosResponseDTOS = servicosMapper.toDTO(servicos);
        return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchOne(@PathVariable(value = "id") Long id){
        try{
            //chamar metodo searchservicoid
            Servicos servicoRecorded = servicosService.searchServicoId(id);
            //transformar objeto servico em dto
            ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servicoRecorded);
            return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTO);
        } catch (ServicoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicosResponseDTO> update(@PathVariable(value = "id") Long id,
                                                      @RequestBody ServicosDTO servicosDTO) {
        try {
            // Transformar DTO em entidade
            Servicos servicos = servicosMapper.toEntity(servicosDTO);
            // Atualizar o serviço
            Servicos servicoAtualizado = servicosService.update(id, servicos);
            // Transformar entidade atualizada em DTO
            ServicosResponseDTO servicosResponseDTO = servicosMapper.toDTO(servicoAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(servicosResponseDTO);
        } catch (ServicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        try {
            servicosService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ServicoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
