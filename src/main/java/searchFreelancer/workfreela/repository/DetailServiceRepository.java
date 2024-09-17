package searchFreelancer.workfreela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;

import java.util.Optional;
@EnableJpaRepositories(basePackages = "searchFreelancer.workfreela.repository")
public interface DetailServiceRepository extends JpaRepository<DetalheServico, DetalheServico_PK> {
    @Query("SELECT d FROM DetalheServico d WHERE d.id = :id")
    Optional<DetalheServico> findByIdWithDetails(@Param("id") DetalheServico_PK id);
}
