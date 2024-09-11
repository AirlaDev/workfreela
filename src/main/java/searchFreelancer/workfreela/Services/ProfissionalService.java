package searchFreelancer.workfreela.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.exceptions.ProfissionalNotFoundException;
import searchFreelancer.workfreela.repository.ProfissionalRepository;

import java.util.List;
import java.util.Optional;

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
    //exibir a lista de serviços (R do crud)
    public List<Profissional> searchAll(){
        return profissionalRepository.findAll();
    }

    //busca para trazer apenas um usuário (buscar pelo id)
    public Profissional searchProfissionalId(Long id) throws ProfissionalNotFoundException {
        //armazena objeto(opt) dos produtos cadastrados
        Optional<Profissional> opt = profissionalRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();

        }
        throw new ProfissionalNotFoundException(("Id não encontrado" + id));
    }

    // Atualizar um profissional (U do CRUD)
    public Profissional update(Long id, Profissional profissional) throws ProfissionalNotFoundException {
        if (!profissionalRepository.existsById(id)) {
            throw new ProfissionalNotFoundException("Id não encontrado: " + id);
        }
        profissional.setId(id); // Garantir que o ID do profissional a ser atualizado é o mesmo do ID fornecido
        try {
            return profissionalRepository.save(profissional);
        } catch (Exception e) {
            logger.error("Erro ao atualizar o profissional", e);
            throw new RuntimeException("Erro ao atualizar o profissional", e);
        }
    }

    // Deletar um profissional (D do CRUD)
    public void delete(Long id) throws ProfissionalNotFoundException {
        if (!profissionalRepository.existsById(id)) {
            throw new ProfissionalNotFoundException("Id não encontrado: " + id);
        }
        try {
            profissionalRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar o profissional", e);
            throw new RuntimeException("Erro ao deletar o profissional", e);
        }
    }

}