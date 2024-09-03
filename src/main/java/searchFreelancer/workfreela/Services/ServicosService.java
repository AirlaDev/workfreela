package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchFreelancer.workfreela.Entity.Servicos;
import searchFreelancer.workfreela.dto.ServicosDTO;
import searchFreelancer.workfreela.repository.ServicosRepository;

@Service

public class ServicosService {

    @Autowired
    private ServicosRepository servicosRepository;

    public Servicos toRecord(Servicos servico){

        return servicosRepository.save(servico);
    }


}

