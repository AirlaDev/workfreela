package searchFreelancer.workfreela.dto;

import lombok.Getter;
import lombok.Setter;
import searchFreelancer.workfreela.Entity.DetalheServico;

@Getter
@Setter
public class DetailServiceDTO {

    private Long id_profissional;
    private Long id_servicos;

    private double preco_servico;
    private int tempo_experiencia;

    // Construtor padrão necessário para a deserialização
    public DetailServiceDTO() {}

    // Construtor para inicializar o DTO a partir da entidade DetalheServico
    public DetailServiceDTO(DetalheServico entity) {
        this.id_profissional = entity.getId().getId_profissional().getId(); // ID do Profissional
        this.id_servicos = entity.getId().getId_servicos().getId(); // ID do Serviço
        this.preco_servico = entity.getPreco_servico();
        this.tempo_experiencia = entity.getTempo_experiencia();
    }
}
