package searchFreelancer.workfreela.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.dto.ProfissionalDTO;
import searchFreelancer.workfreela.dto.ProfissionalResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfissionalMapper {

  @Autowired
  private ModelMapper modelMapper;

  public Profissional toEntity(ProfissionalDTO dto) {
    return modelMapper.map(dto, Profissional.class);
  }

  public ProfissionalResponseDTO toDTO(Profissional entity) {
    return modelMapper.map(entity, ProfissionalResponseDTO.class);
  }

  public List<ProfissionalResponseDTO> toDTO(List<Profissional> profissionals) {
    return profissionals.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
  }
  // Convert Entity to DTO with the same ID
  public ProfissionalDTO toDTO(Profissional entity, Long id) {
    if (entity == null) {
      return null;
    }
    ProfissionalDTO dto = modelMapper.map(entity, ProfissionalDTO.class);
    dto.setId_profissional(id); // Ensure the ID is set correctly
    return dto;
  }
}
