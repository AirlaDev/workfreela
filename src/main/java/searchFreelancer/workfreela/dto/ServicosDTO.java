package searchFreelancer.workfreela.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import searchFreelancer.workfreela.Entity.Servicos;

@Getter
@Setter
public class ServicosDTO {

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 4, message = "O nome deve ter no mínimo 04 caracteres")
    private long id;
    private String nome_servico;
    private String descricao;
    private String categoria;

    // Construtor padrão (necessário para Jackson)
    public ServicosDTO() {
    }

    // Construtor que aceita uma entidade Servicos
    public ServicosDTO(Servicos entity) {
        this.id = entity.getId();
        this.nome_servico = entity.getNome_servico();
        this.descricao = entity.getDescricao();
        this.categoria = entity.getCategoria();
    }
}
