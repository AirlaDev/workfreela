package searchFreelancer.workfreela.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import searchFreelancer.workfreela.Entity.Profissional;

@Getter
@Setter
public class ProfissionalDTO {

    @NotNull(message = "O ID do profissional deve ser informado")
    private Long id_profissional;

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 4, message = "O nome deve ter no mínimo 04 caracteres")
    private String nome;

    @Email(message = "Email deve ser válido")
    private String email;

    private String telefone;

    private String sexo;

    private String idade;

    // Construtor sem argumentos necessário para a serialização/deserialização
    public ProfissionalDTO() {
    }

    // Construtor que recebe uma entidade Profissional
    public ProfissionalDTO(Profissional entity) {
        this.id_profissional = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
        this.sexo = entity.getSexo();
        this.idade = entity.getIdade();
    }
}
