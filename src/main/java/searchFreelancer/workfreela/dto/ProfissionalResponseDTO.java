package searchFreelancer.workfreela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfissionalResponseDTO {
    @JsonProperty("id_profissional")
    private Long id_profissional;
    private String nome;
    private String email;
    private String telefone;
    private String sexo;
    private String idade;

}
