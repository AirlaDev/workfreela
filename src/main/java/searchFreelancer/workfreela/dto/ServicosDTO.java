package searchFreelancer.workfreela.dto;

import lombok.Data;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Entity.Servicos;

@Data
public class ServicosDTO {
    private long id;
    private String nome_servico;
    private String descricao;
    private String categoria;

    public ServicosDTO(Servicos entity){
        id = entity.getId();
        nome_servico = entity.getNome_servico();
        descricao = entity.getDescricao();
        categoria = entity.getCategoria();

    }

}
