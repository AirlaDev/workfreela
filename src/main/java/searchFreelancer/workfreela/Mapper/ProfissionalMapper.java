package searchFreelancer.workfreela.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.dto.ProfissionalDTO;
import searchFreelancer.workfreela.dto.ProfissionalResponseDTO;


import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalMapper {
  @Autowired
  private ModelMapper modelMapper;

  public Profissional toEntity(ProfissionalDTO dto){
    Profissional entity = modelMapper.map(dto, Profissional.class);
    return entity;
  }

  public ProfissionalResponseDTO toDTO(Profissional entity){
    ProfissionalResponseDTO dto = modelMapper.map(entity, ProfissionalResponseDTO.class);
    return dto;
  }

  public List<ProfissionalResponseDTO> toDTO(List<Profissional> profissionals){
    return profissionals
            .stream()
            .map(profissional -> toDTO(profissional))
            .collect(Collectors.toList());
  }

}
