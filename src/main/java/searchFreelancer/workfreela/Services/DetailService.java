package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.factory.DetalheServicoFactory;
import searchFreelancer.workfreela.Mapper.DetailServiceMapper;
import searchFreelancer.workfreela.exceptions.DetalhesNotFoundException;
import searchFreelancer.workfreela.repository.DetailServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {

    @Autowired
    private DetailServiceRepository detailServiceRepository;

    @Autowired
    private DetailServiceMapper detailServiceMapper;

    private final DetalheServicoFactory detalheServicoFactory;

    @Autowired
    public DetailService(DetalheServicoFactory detalheServicoFactory) {
        this.detalheServicoFactory = detalheServicoFactory;
    }

    // Criar um novo detalhe de serviço
    public DetalheServico toRecord(DetalheServico detalheServico) {
        if (detalheServico == null) {
            throw new IllegalArgumentException("Detalhe do serviço não pode ser nulo");
        }

        try {
            // Criação usando o Factory Method
            DetalheServico newDetalheServico = detalheServicoFactory.create();
            newDetalheServico.setPreco_servico(detalheServico.getPreco_servico());
            newDetalheServico.setTempo_experiencia(detalheServico.getTempo_experiencia());
            newDetalheServico.setId(detalheServico.getId());

            return detailServiceRepository.save(newDetalheServico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o detalhe do serviço", e);
        }
    }

    // Listar todos os detalhes de serviço
    public List<DetalheServico> searchAll() {
        return detailServiceRepository.findAll();
    }

    // Buscar um detalhe de serviço pelo ID e retornar o DTO
    public DetailResponseDTO searchDetalheById(DetalheServico_PK id) throws DetalhesNotFoundException {
        Optional<DetalheServico> opt = detailServiceRepository.findById(id);
        if (opt.isPresent()) {
            DetalheServico detalheServico = opt.get();
            // Converte a entidade para DTO usando o mapper
            return detailServiceMapper.toDTO(detalheServico);
        }
        throw new DetalhesNotFoundException("Detalhe de serviço não encontrado.");
    }

    // Atualizar um detalhe de serviço existente
    public DetalheServico update(DetalheServico_PK id, DetalheServico detalheServico) throws DetalhesNotFoundException {
        if (!detailServiceRepository.existsById(id)) {
            throw new DetalhesNotFoundException("Detalhe de serviço não encontrado: " + id);
        }
        detalheServico.setId(id);  // Garantir que o ID corresponde ao detalhe de serviço atualizado
        return detailServiceRepository.save(detalheServico);
    }

    // Deletar um detalhe de serviço
    public void delete(DetalheServico_PK id) throws DetalhesNotFoundException {
        if (!detailServiceRepository.existsById(id)) {
            throw new DetalhesNotFoundException("Detalhe de serviço não encontrado: " + id);
        }
        detailServiceRepository.deleteById(id);
    }
}
