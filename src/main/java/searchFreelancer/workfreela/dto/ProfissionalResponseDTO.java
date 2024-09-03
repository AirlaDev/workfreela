package searchFreelancer.workfreela.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfissionalResponseDTO {
    @JsonProperty("id_profissional")
    private Long id_profissional;
    private String nome;
    private String email;
    private String telefone;
    private String sexo;
    private String idade;

    public ProfissionalResponseDTO() {
    }

    public ProfissionalResponseDTO(Long id_profissional, String nome, String email, String telefone, String sexo, String idade) {
        this.id_profissional = id_profissional;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.idade = idade;
    }

}
