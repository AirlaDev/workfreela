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

    public ProfissionalDTO(Profissional entity){
        id_profissional = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        sexo = entity.getSexo();
        idade = entity.getIdade();
    }

}
