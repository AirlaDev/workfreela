package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.exceptions.DetalhesNotFoundException;
import searchFreelancer.workfreela.repository.DetailServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {

    @Autowired
    private DetailServiceRepository detailServiceRepository;

    public DetalheServico toRecord(DetalheServico detalheServico) {
        return detailServiceRepository.save(detalheServico);
    }

    public List<DetalheServico> searchAll() {
        return detailServiceRepository.findAll();
    }

    public DetalheServico searchDetalheById(DetalheServico_PK id) throws DetalhesNotFoundException {
        Optional<DetalheServico> opt = detailServiceRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new DetalhesNotFoundException("ID não encontrado: " + id);
    }
}































