package searchFreelancer.workfreela.facade;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import searchFreelancer.workfreela.Entity.Servicos;

import java.util.List;

@NoRepositoryBean
public interface ServicoFacade extends JpaRepository<Servicos, Long> {
    void flush();
    <S extends Servicos> S saveAndFlush(S entity);
    <S extends Servicos> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<Servicos> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    Servicos getReferenceById(Long id);
}
