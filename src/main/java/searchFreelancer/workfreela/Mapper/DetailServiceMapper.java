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
        if (dto.getId_profissional() != null) {
            id.setId_profissional(new Profissional(dto.getId_profissional()));
        }
        if (dto.getId_servicos() != null) {
            id.setId_servicos(new Servicos(dto.getId_servicos()));
        } else {
            throw new IllegalArgumentException("ID de serviço não pode ser nulo");
        }
        entity.setId(id);
        return entity;
    }

    public DetailResponseDTO toDTO(DetalheServico entity) {
        DetailResponseDTO dto = new DetailResponseDTO();
        if (entity.getId().getId_profissional() != null) {
            dto.setId_profissional(entity.getId().getId_profissional().getId());
            dto.setNome(entity.getId().getId_profissional().getNome()); // Corrigido para garantir o nome
        } else {
            dto.setNome("Nome não encontrado");
        }
        if (entity.getId().getId_servicos() != null) {
            dto.setId_servicos(entity.getId().getId_servicos().getId());
            dto.setNome_servico(entity.getId().getId_servicos().getNome_servico()); // Corrigido para garantir o nome do serviço
        } else {
            dto.setNome_servico("Serviço não encontrado");
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