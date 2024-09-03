package searchFreelancer.workfreela.Entity;

//classe auxiliar para pegar a chave primaria das outras classes que ela vai relacionar

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable

public class DetailService_PK {
    private long id_pk;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional id_profissional;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Servicos id_servicos;


    public DetailService_PK() {
    }

    //compara o objeto


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailService_PK that = (DetailService_PK) o;
        return Objects.equals(id_profissional, that.id_profissional) && Objects.equals(id_servicos, that.id_servicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_profissional, id_servicos);
    }
}
