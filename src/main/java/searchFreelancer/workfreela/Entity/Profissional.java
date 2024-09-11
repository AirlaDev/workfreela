package searchFreelancer.workfreela.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "profissional")

public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String sexo;
    private String idade;

    public Profissional() {}

    // Construtor que aceita apenas o ID
    public Profissional(Long id) {
        this.id = id;
    }

    public Profissional(Long id, String nome, String email, String telefone, String sexo, String idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
