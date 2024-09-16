package searchFreelancer.workfreela.factory;

import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.Servicos;

@Component
public class ServicosFactory implements EntityFactory<Servicos> {
    @Override
    public Servicos create() {
        return new Servicos();
    }
}
