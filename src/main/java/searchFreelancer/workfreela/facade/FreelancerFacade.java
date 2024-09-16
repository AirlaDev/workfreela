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

    // Métodos de Adição

    /**
     * Adiciona um novo profissional ao sistema.
     *
     * @param profissional Profissional a ser adicionado.
     * @return Profissional salvo no banco de dados.
     */
    public Profissional addProfissional(Profissional profissional) {
        return profissionalService.toRecord(profissional);
    }

    /**
     * Adiciona um novo serviço ao sistema.
     *
     * @param servico Serviço a ser adicionado.
     * @return Serviço salvo no banco de dados.
     */
    public Servicos addServico(Servicos servico) {
        return servicosService.toRecord(servico);
    }

    /**
     * Adiciona um novo detalhe de serviço ao sistema.
     *
     * @param detalheServico Detalhe do serviço a ser adicionado.
     * @return Detalhe do serviço salvo no banco de dados.
     */
    public DetalheServico addDetalheServico(DetalheServico detalheServico) {
        return detailService.toRecord(detalheServico);
    }

    // Métodos de Recuperação

    /**
     * Recupera todos os profissionais cadastrados no sistema.
     *
     * @return Lista de profissionais.
     */
    public List<Profissional> getAllProfissionais() {
        return profissionalService.searchAll();
    }

    /**
     * Recupera um profissional pelo ID.
     *
     * @param id ID do profissional.
     * @return Profissional encontrado.
     * @throws ProfissionalNotFoundException Caso o profissional não seja encontrado.
     */
    public Profissional getProfissionalById(Long id) throws ProfissionalNotFoundException {
        return profissionalService.searchProfissionalId(id);
    }

    /**
     * Recupera todos os serviços cadastrados no sistema.
     *
     * @return Lista de serviços.
     */
    public List<Servicos> getAllServicos() {
        return servicosService.searchAll();
    }

    /**
     * Recupera um serviço pelo ID.
     *
     * @param id ID do serviço.
     * @return Serviço encontrado.
     * @throws ServicoNotFoundException Caso o serviço não seja encontrado.
     */
    public Servicos getServicoById(Long id) throws ServicoNotFoundException {
        return servicosService.searchServicoId(id);
    }

    /**
     * Recupera todos os detalhes de serviço cadastrados.
     *
     * @return Lista de detalhes de serviço.
     */
    public List<DetalheServico> getAllDetalheServicos() {
        return detailService.searchAll();
    }

    /**
     * Recupera um detalhe de serviço pelo ID do profissional e ID do serviço.
     *
     * @param profissionalId ID do profissional.
     * @param servicoId      ID do serviço.
     * @return Detalhe do serviço.
     * @throws DetalhesNotFoundException Caso o detalhe não seja encontrado.
     */

    // Busca o detalhe do serviço e retorna o DTO com nome do profissional e do serviço
    public DetailResponseDTO getDetalheServicoById(Long profissionalId, Long servicoId) throws DetalhesNotFoundException {
        DetalheServico_PK id = new DetalheServico_PK();
        id.setId_profissional(new Profissional(profissionalId));
        id.setId_servicos(new Servicos(servicoId));

        // Chama o serviço para buscar os detalhes
        return detailService.searchDetalheById(id);
    }

    // Métodos de Atualização

    /**
     * Atualiza as informações de um profissional pelo ID.
     *
     * @param id           ID do profissional.
     * @param profissional Dados do profissional a serem atualizados.
     * @return Profissional atualizado.
     * @throws ProfissionalNotFoundException Caso o profissional não seja encontrado.
     */
    public Profissional updateProfissional(Long id, Profissional profissional) throws ProfissionalNotFoundException {
        return profissionalService.update(id, profissional);
    }

    /**
     * Atualiza as informações de um serviço pelo ID.
     *
     * @param id      ID do serviço.
     * @param servico Dados do serviço a serem atualizados.
     * @return Serviço atualizado.
     * @throws ServicoNotFoundException Caso o serviço não seja encontrado.
     */
    public Servicos updateServico(Long id, Servicos servico) throws ServicoNotFoundException {
        return servicosService.update(id, servico);
    }

    /**
     * Atualiza as informações de um detalhe de serviço.
     *
     * @param id             Chave composta do detalhe de serviço.
     * @param detalheServico Dados a serem atualizados.
     * @return Detalhe do serviço atualizado.
     * @throws DetalhesNotFoundException Caso o detalhe não seja encontrado.
     */
    public DetalheServico updateDetalheServico(DetalheServico_PK id, DetalheServico detalheServico) throws DetalhesNotFoundException {
        return detailService.update(id, detalheServico);
    }

    // Métodos de Deleção

    /**
     * Deleta um profissional pelo ID.
     *
     * @param id ID do profissional a ser deletado.
     * @throws ProfissionalNotFoundException Caso o profissional não seja encontrado.
     */
    public void deleteProfissional(Long id) throws ProfissionalNotFoundException {
        profissionalService.delete(id);
    }

    /**
     * Deleta um serviço pelo ID.
     *
     * @param id ID do serviço a ser deletado.
     * @throws ServicoNotFoundException Caso o serviço não seja encontrado.
     */
    public void deleteServico(Long id) throws ServicoNotFoundException {
        servicosService.delete(id);
    }

    /**
     * Deleta um detalhe de serviço pelo ID do profissional e serviço.
     *
     * @param id Chave composta do detalhe de serviço.
     * @throws DetalhesNotFoundException Caso o detalhe não seja encontrado.
     */
    public void deleteDetalheServico(DetalheServico_PK id) throws DetalhesNotFoundException {
        detailService.delete(id);
    }
}