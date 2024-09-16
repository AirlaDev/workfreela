package searchFreelancer.workfreela.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.factory.ProfissionalFactory;
import searchFreelancer.workfreela.Mapper.ProfissionalMapper;
import searchFreelancer.workfreela.exceptions.ProfissionalNotFoundException;
import searchFreelancer.workfreela.repository.ProfissionalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionalService.class);

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    private final ProfissionalFactory profissionalFactory;

    @Autowired
    public ProfissionalService(ProfissionalFactory profissionalFactory) {
        this.profissionalFactory = profissionalFactory;
    }

    // Criar um novo profissional
    public Profissional toRecord(Profissional profissional) {
        if (profissional == null) {
            logger.error("Profissional não pode ser nulo");
            throw new IllegalArgumentException("Profissional não pode ser nulo");
        }

        try {
            // Criação usando o Factory Method
            Profissional newProfissional = profissionalFactory.create();
            newProfissional.setNome(profissional.getNome());
            newProfissional.setEmail(profissional.getEmail());
            newProfissional.setTelefone(profissional.getTelefone());
            newProfissional.setSexo(profissional.getSexo());
            newProfissional.setIdade(profissional.getIdade());

            return profissionalRepository.save(newProfissional);
        } catch (Exception e) {
            logger.error("Erro ao salvar o profissional", e);
            throw new RuntimeException("Erro ao salvar o profissional", e);
        }
    }

    // Listar todos os profissionais
    public List<Profissional> searchAll() {
        return profissionalRepository.findAll();
    }

    // Buscar um profissional pelo ID
    public Profissional searchProfissionalId(Long id) throws ProfissionalNotFoundException {
        Optional<Profissional> opt = profissionalRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ProfissionalNotFoundException("Profissional não encontrado: " + id);
    }

    // Atualizar um profissional existente
    public Profissional update(Long id, Profissional profissional) throws ProfissionalNotFoundException {
        if (!profissionalRepository.existsById(id)) {
            throw new ProfissionalNotFoundException("Profissional não encontrado: " + id);
        }
        profissional.setId(id);  // Garantir que o ID corresponde ao profissional atualizado
        return profissionalRepository.save(profissional);
    }

    // Deletar um profissional
    public void delete(Long id) throws ProfissionalNotFoundException {
        if (!profissionalRepository.existsById(id)) {
            throw new ProfissionalNotFoundException("Profissional não encontrado: " + id);
        }
        profissionalRepository.deleteById(id);
    }
}