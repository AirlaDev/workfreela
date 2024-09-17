package searchFreelancer.workfreela.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.DetalheServico;
import searchFreelancer.workfreela.Entity.DetalheServico_PK;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.Services.DetailService;
import searchFreelancer.workfreela.Services.ProfissionalService;
import searchFreelancer.workfreela.Services.ServicosService;
import searchFreelancer.workfreela.dto.DetailResponseDTO;
import searchFreelancer.workfreela.exceptions.DetalhesNotFoundException;
import searchFreelancer.workfreela.exceptions.ProfissionalNotFoundException;
import searchFreelancer.workfreela.exceptions.ServicoNotFoundException;

import java.util.List;

@Component
public class FreelancerFacade {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ServicosService servicosService;

    @Autowired
    private DetailService detailService;

    public Profissional addProfissional(Profissional profissional) {
        return profissionalService.toRecord(profissional);
    }


    public Servicos addServico(Servicos servico) {
        return servicosService.toRecord(servico);
    }


    public DetalheServico addDetalheServico(DetalheServico detalheServico) {
        return detailService.toRecord(detalheServico);
    }


    public List<Profissional> getAllProfissionais() {
        return profissionalService.searchAll();
    }

    public Profissional getProfissionalById(Long id) throws ProfissionalNotFoundException {
        return profissionalService.searchProfissionalId(id);
    }


    public List<Servicos> getAllServicos() {
        return servicosService.searchAll();
    }


    public Servicos getServicoById(Long id) throws ServicoNotFoundException {
        return servicosService.searchServicoId(id);
    }


    public List<DetalheServico> getAllDetalheServicos() {
        return detailService.searchAll();
    }


    // Busca o detalhe do serviço e retorna o DTO com nome do profissional e do serviço
    public DetailResponseDTO getDetalheServicoById(Long profissionalId, Long servicoId) throws DetalhesNotFoundException {
        DetalheServico_PK id = new DetalheServico_PK();
        id.setId_profissional(new Profissional(profissionalId));
        id.setId_servicos(new Servicos(servicoId));

        // Chama o serviço para buscar os detalhes
        return detailService.searchDetalheById(id);
    }


    public Profissional updateProfissional(Long id, Profissional profissional) throws ProfissionalNotFoundException {
        return profissionalService.update(id, profissional);
    }

    public Servicos updateServico(Long id, Servicos servico) throws ServicoNotFoundException {
        return servicosService.update(id, servico);
    }

    public DetalheServico updateDetalheServico(DetalheServico_PK id, DetalheServico detalheServico) throws DetalhesNotFoundException {
        return detailService.update(id, detalheServico);
    }


    public void deleteProfissional(Long id) throws ProfissionalNotFoundException {
        profissionalService.delete(id);
    }


    public void deleteServico(Long id) throws ServicoNotFoundException {
        servicosService.delete(id);
    }


    public void deleteDetalheServico(DetalheServico_PK id) throws DetalhesNotFoundException {
        detailService.delete(id);
    }
}