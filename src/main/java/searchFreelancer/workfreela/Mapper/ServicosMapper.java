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

    public Servicos toEntity(ServicosDTO dto){
        Servicos entity = modelMapper.map(dto, Servicos.class);
        return entity;
    }

    public ServicosResponseDTO toDTO(Servicos entity){
        ServicosResponseDTO dto = modelMapper.map(entity, ServicosResponseDTO.class);
        return dto;
    }

    public List<ServicosResponseDTO> toDTO(List<Servicos> servicos){
        return servicos
                .stream()
                .map(servicos1 -> toDTO(servicos1))
                .collect(Collectors.toList());
    }

}

