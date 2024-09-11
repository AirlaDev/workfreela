package searchFreelancer.workfreela.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.dto.DetailServiceDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailServiceMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DetalheServico toEntity(DetailServiceDTO dto) {
        DetalheServico entity = modelMapper.map(dto, DetalheServico.class);
        DetalheServico_PK id = new DetalheServico_PK();
        id.setId_profissional(new Profissional(dto.getId_profissional()));
        id.setId_servicos(new Servicos(dto.getId_servicos()));
        entity.setId(id);
        return entity;
    }

    public DetailResponseDTO toDTO(DetalheServico entity) {
        DetailResponseDTO dto = new DetailResponseDTO();
        // Puxando o ID e o nome do profissional
        if (entity.getId().getId_profissional() != null) {
            dto.setId_profissional(entity.getId().getId_profissional().getId());
            dto.setNome(entity.getId().getId_profissional().getNome());  // Nome do profissional
        }
        // Puxando o ID e o nome do serviço
        if (entity.getId().getId_servicos() != null) {
            dto.setId_servicos(entity.getId().getId_servicos().getId());
            dto.setNome_servico(entity.getId().getId_servicos().getNome_servico());  // Nome do serviço
        }
        dto.setPreco(entity.getPreco_servico());
        dto.setTempo_experiencia(entity.getTempo_experiencia());
        return dto;
    }

    public List<DetailResponseDTO> toDTO(List<DetalheServico> detalheServicos) {
        return detalheServicos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
