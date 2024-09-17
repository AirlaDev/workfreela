package searchFreelancer.workfreela.Services;

import searchFreelancer.workfreela.Mapper.DetailServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.factory.DetalheServicoFactory;
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

    @Autowired
    private DetalheServicoFactory detalheServicoFactory;

    // Criar um novo detalhe de serviço
    public DetalheServico toRecord(DetalheServico detalheServico) {
        DetalheServico newDetalheServico = detalheServicoFactory.create();
        newDetalheServico.setPreco_servico(detalheServico.getPreco_servico());
        newDetalheServico.setTempo_experiencia(detalheServico.getTempo_experiencia());
        newDetalheServico.setId(detalheServico.getId());
        return detailServiceRepository.save(newDetalheServico);
    }

    // Lista todos os detalhes de serviço
    public List<DetalheServico> searchAll() {
        return detailServiceRepository.findAll(); // Certifique-se de que este método está disponível no repositório
    }

    public DetailResponseDTO searchDetalheById(DetalheServico_PK id) throws DetalhesNotFoundException {
        // Usando a nova consulta com JOIN FETCH
        Optional<DetalheServico> opt = detailServiceRepository.findByIdWithDetails(id);
        if (opt.isPresent()) {
            DetalheServico detalheServico = opt.get();
            return detailServiceMapper.toDTO(detalheServico);
        }
        throw new DetalhesNotFoundException("Detalhe de serviço não encontrado.");
    }

    /*
    // Buscar um detalhe de serviço pelo ID
    public DetailResponseDTO searchDetalheById(DetalheServico_PK id) throws DetalhesNotFoundException {
        Optional<DetalheServico> opt = detailServiceRepository.findById(id);
        if (opt.isPresent()) {
            DetalheServico detalheServico = opt.get();
            return detailServiceMapper.toDTO(detalheServico);
        }
        throw new DetalhesNotFoundException("Detalhe de serviço não encontrado.");
    }*/

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
