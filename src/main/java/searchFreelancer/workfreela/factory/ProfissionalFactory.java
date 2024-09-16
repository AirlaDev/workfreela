package searchFreelancer.workfreela.factory;

import org.springframework.stereotype.Component;
import searchFreelancer.workfreela.Entity.Profissional;

@Component
public class ProfissionalFactory implements EntityFactory<Profissional> {
    @Override
    public Profissional create() {
        return new Profissional();
    }
}
