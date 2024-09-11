package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.exceptions.ServicoNotFoundException;
import searchFreelancer.workfreela.repository.ServicosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {
    @Autowired
    private ServicosRepository servicosRepository;

    // Gravar (criar) no banco de dados com o método toRecord (C do CRUD)
    public Servicos toRecord(Servicos servico) {
        return servicosRepository.save(servico);
    }

    // Exibir a lista de serviços (R do CRUD)
    public List<Servicos> searchAll() {
        return servicosRepository.findAll();
    }

    // Buscar apenas um usuário (buscar pelo id)
    public Servicos searchServicoId(Long id) throws ServicoNotFoundException {
        Optional<Servicos> opt = servicosRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ServicoNotFoundException("Id não encontrado: " + id);
    }

    // Atualizar um serviço (U do CRUD)
    public Servicos update(Long id, Servicos servico) throws ServicoNotFoundException {
        if (!servicosRepository.existsById(id)) {
            throw new ServicoNotFoundException("Id não encontrado: " + id);
        }
        servico.setId(id); // Garantir que o ID do serviço a ser atualizado é o mesmo do ID fornecido
        return servicosRepository.save(servico);
    }

    // Deletar um serviço (D do CRUD)
    public void delete(Long id) throws ServicoNotFoundException {
        if (!servicosRepository.existsById(id)) {
            throw new ServicoNotFoundException("Id não encontrado: " + id);
        }
        servicosRepository.deleteById(id);
    }
}
