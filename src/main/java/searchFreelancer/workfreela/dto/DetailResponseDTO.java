package searchFreelancer.workfreela.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Adicionando construtor sem argumentos
public class DetailResponseDTO {
    private long id_profissional;
    private String nome;
    private long id_servicos;
    private String nome_servico;
    private double preco;
    private int tempo_experiencia;
}
