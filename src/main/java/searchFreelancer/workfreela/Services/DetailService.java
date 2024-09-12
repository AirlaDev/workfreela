package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.exceptions.DetalhesNotFoundException;
import searchFreelancer.workfreela.repository.DetailServiceRepository;
import searchFreelancer.workfreela.repository.ProfissionalRepository;
import searchFreelancer.workfreela.repository.ServicosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {

    @Autowired
    private DetailServiceRepository detailServiceRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository; // Repositório para Profissional

    @Autowired
    private ServicosRepository servicosRepository; // Repositório para Serviço

    public DetalheServico toRecord(DetalheServico detalheServico) {
        return detailServiceRepository.save(detalheServico);
    }

    public List<DetalheServico> searchAll() {
        return detailServiceRepository.findAll();
    }

    public DetailResponseDTO searchDetalheById(DetalheServico_PK id) throws DetalhesNotFoundException {
        Optional<DetalheServico> opt = detailServiceRepository.findById(id);
        if (opt.isPresent()) {
            DetalheServico detalheServico = opt.get();

            // Buscar o nome do profissional
            Profissional profissional = profissionalRepository.findById(
                            detalheServico.getId().getId_profissional().getId())
                    .orElseThrow(() -> new DetalhesNotFoundException("Profissional não encontrado"));
            String nomeProfissional = profissional.getNome();

            // Buscar o nome do serviço
            Servicos servico = servicosRepository.findById(
                            detalheServico.getId().getId_servicos().getId())
                    .orElseThrow(() -> new DetalhesNotFoundException("Serviço não encontrado"));
            String nomeServico = servico.getNome_servico();

            // Criar o DTO com os dados completos
            DetailResponseDTO dto = new DetailResponseDTO();
            dto.setId_profissional(detalheServico.getId().getId_profissional().getId());
            dto.setNome(nomeProfissional); // Nome do profissional
            dto.setId_servicos(detalheServico.getId().getId_servicos().getId());
            dto.setNome_servico(nomeServico); // Nome do serviço
            dto.setPreco(detalheServico.getPreco_servico());
            dto.setTempo_experiencia(detalheServico.getTempo_experiencia());

            return dto;
        }
        throw new DetalhesNotFoundException("ID não encontrado: " + id);
    }

    // Método de atualização (update)
    public DetalheServico update(DetalheServico_PK id, DetalheServico detalheServico) throws DetalhesNotFoundException {
        if (!detailServiceRepository.existsById(id)) {
            throw new DetalhesNotFoundException("ID não encontrado: " + id);
        }
        // Garantir que o ID do DetalheServico a ser atualizado seja o mesmo do ID fornecido
        detalheServico.setId(id);
        return detailServiceRepository.save(detalheServico);
    }

    // Método de deleção (delete)
    public void delete(DetalheServico_PK id) throws DetalhesNotFoundException {
        if (!detailServiceRepository.existsById(id)) {
            throw new DetalhesNotFoundException("ID não encontrado: " + id);
        }
        detailServiceRepository.deleteById(id);
    }
}
