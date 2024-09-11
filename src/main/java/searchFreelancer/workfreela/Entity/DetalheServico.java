package searchFreelancer.workfreela.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "detalhe_servicos")
public class DetalheServico {

    @EmbeddedId
    private DetalheServico_PK id = new DetalheServico_PK();

    private double preco_servico;
    private int tempo_experiencia;

    public DetalheServico() {
    }

    public DetalheServico(Servicos servico, Profissional profissional, double preco_servico, int tempo_experiencia) {
        id.setId_servicos(servico);
        id.setId_profissional(profissional);
        this.preco_servico = preco_servico;
        this.tempo_experiencia = tempo_experiencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalheServico that = (DetalheServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
