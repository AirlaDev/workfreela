package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.factory.ServicosFactory;
import searchFreelancer.workfreela.Mapper.ServicosMapper;
import searchFreelancer.workfreela.exceptions.ServicoNotFoundException;
import searchFreelancer.workfreela.repository.ServicosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private ServicosMapper servicosMapper;

    private final ServicosFactory servicosFactory;

    @Autowired
    public ServicosService(ServicosFactory servicosFactory) {
        this.servicosFactory = servicosFactory;
    }

    // Criar um novo serviço
    public Servicos toRecord(Servicos servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo");
        }

        try {
            // Criação usando o Factory Method
            Servicos newServico = servicosFactory.create();
            newServico.setNome_servico(servico.getNome_servico());
            newServico.setDescricao(servico.getDescricao());
            newServico.setCategoria(servico.getCategoria());

            return servicosRepository.save(newServico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o serviço", e);
        }
    }

    // Listar todos os serviços
    public List<Servicos> searchAll() {
        return servicosRepository.findAll();
    }

    // Buscar um serviço pelo ID
    public Servicos searchServicoId(Long id) throws ServicoNotFoundException {
        Optional<Servicos> opt = servicosRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ServicoNotFoundException("Serviço não encontrado: " + id);
    }

    // Atualizar um serviço existente
    public Servicos update(Long id, Servicos servico) throws ServicoNotFoundException {
        if (!servicosRepository.existsById(id)) {
            throw new ServicoNotFoundException("Serviço não encontrado: " + id);
        }
        servico.setId(id);  // Garantir que o ID corresponde ao serviço atualizado
        return servicosRepository.save(servico);
    }

    // Deletar um serviço
    public void delete(Long id) throws ServicoNotFoundException {
        if (!servicosRepository.existsById(id)) {
            throw new ServicoNotFoundException("Serviço não encontrado: " + id);
        }
        servicosRepository.deleteById(id);
    }

}
