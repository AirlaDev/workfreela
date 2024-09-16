package searchFreelancer.workfreela.factory;

import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.DetalheServico;

@Component
public class DetalheServicoFactory implements EntityFactory<DetalheServico> {
    @Override
    public DetalheServico create() {
        return new DetalheServico();
    }
}
