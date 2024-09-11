package searchFreelancer.workfreela.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.dto.ServicosDTO;
import searchFreelancer.workfreela.dto.ServicosResponseDTO;
import java.util.stream.Collectors;
import java.util.List;

@Component
public class ServicosMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Servicos toEntity(ServicosDTO dto) {
        return modelMapper.map(dto, Servicos.class);
    }

    public ServicosResponseDTO toDTO(Servicos entity) {
        return modelMapper.map(entity, ServicosResponseDTO.class);
    }

    public List<ServicosResponseDTO> toDTO(List<Servicos> servicos) {
        return servicos
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
