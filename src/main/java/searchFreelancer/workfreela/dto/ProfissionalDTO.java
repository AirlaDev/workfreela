package searchFreelancer.workfreela.dto;

import lombok.Data;
import searchFreelancer.workfreela.Entity.Profissional;

@Data
public class ProfissionalDTO {

    private Long id_profissional;
    private String nome;
    private String email;
    private String telefone;
    private String sexo;
    private String idade;

    // Construtor padrão
    public ProfissionalDTO() {
    }

    // Construtor com parâmetros
    public ProfissionalDTO(Long id_profissional, String nome, String email, String telefone, String sexo, String idade) {
        this.id_profissional = id_profissional;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.idade = idade;
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
