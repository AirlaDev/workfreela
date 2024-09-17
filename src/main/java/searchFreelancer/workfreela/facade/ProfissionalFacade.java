package searchFreelancer.workfreela.facade;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import searchFreelancer.workfreela.Entity.Profissional;

import java.util.List;

@NoRepositoryBean
public interface ProfissionalFacade extends JpaRepository<Profissional, Long> {
    void flush();
    <S extends Profissional> S saveAndFlush(S entity);
    <S extends Profissional> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<Profissional> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    Profissional getReferenceById(Long id);
}
