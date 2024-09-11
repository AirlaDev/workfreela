package searchFreelancer.workfreela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;

public interface DetailServiceRepository extends JpaRepository<DetalheServico, DetalheServico_PK> {
}
