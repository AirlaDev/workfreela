package searchFreelancer.workfreela.facade;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;


import java.util.List;

@NoRepositoryBean
public interface DetalheServicoFacade extends JpaRepository<DetalheServico, DetalheServico_PK> {
    void flush();
    <S extends DetalheServico> S saveAndFlush(S entity);
    <S extends DetalheServico> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<DetalheServico> entities);
    void deleteAllByIdInBatch(Iterable<DetalheServico_PK> ids);
    DetalheServico getReferenceById(DetalheServico_PK id);
}
