package searchFreelancer.workfreela.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "servicos")
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome_servico;
    private String descricao;
    private String categoria;

    public Servicos() {}

    // Construtor que aceita apenas o ID
    public Servicos(Long id) {
        this.id = id;
    }

    // Construtor completo
    public Servicos(long id, String nome_servico, String descricao, String categoria) {
        this.id = id;
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
        return Objects.hash(id);
    }
}
