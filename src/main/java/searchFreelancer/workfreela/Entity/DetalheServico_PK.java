package searchFreelancer.workfreela.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DetalheServico_PK implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER) // Força o carregamento dos dados relacionados
    @JoinColumn(name = "id_profissional")
    private Profissional id_profissional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_service")
    private Servicos id_servicos;

    public DetalheServico_PK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalheServico_PK that = (DetalheServico_PK) o;
        return Objects.equals(id_profissional, that.id_profissional) &&
                Objects.equals(id_servicos, that.id_servicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_profissional, id_servicos);
    }
}
