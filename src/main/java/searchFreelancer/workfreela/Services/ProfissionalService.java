package searchFreelancer.workfreela.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.repository.ProfissionalRepository;

@Service
public class ProfissionalService {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionalService.class);

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional toRecord(Profissional profissional) {
        if (profissional == null) {
            logger.error("Profissional não pode ser nulo");
            throw new IllegalArgumentException("Profissional não pode ser nulo");
        }
        try {
            return profissionalRepository.save(profissional);
        } catch (Exception e) {
            logger.error("Erro ao salvar o profissional", e);
            throw new RuntimeException("Erro ao salvar o profissional", e);
        }
    }
}