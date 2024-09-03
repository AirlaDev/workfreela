package searchFreelancer.workfreela.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Detalhe_Servicos")

public class DetailService {
    @EmbeddedId //juntar a chave primaria daqui com a da outra tabela

    private DetailService_PK id = new DetailService_PK();
    private double preco_servico;
    private int tempo_experiencia;

    public DetailService() {
    }

    public DetailService(Servicos servico, Profissional profissinal, double preco_servico, int tempo_experiencia) {
        id.setId_servicos(servico);
        id.setId_profissional(profissinal);
        this.id = id;
        this.preco_servico = preco_servico;
        this.tempo_experiencia = tempo_experiencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailService that = (DetailService) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
