package searchFreelancer.workfreela.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Servicos")

public class Servicos {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String nome_servico;
    private String descricao;
    private String categoria;

    public Servicos() {
    }

    public Servicos(long idServico, String nome_servico, String descricao, String categoria) {
        this.id = idServico;
        this.nome_servico = nome_servico;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicos servicos = (Servicos) o;
        return id == servicos.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
