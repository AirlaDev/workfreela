package searchFreelancer.workfreela.Services;

import org.springframework.beans.factory.annotation.Autowired;
import searchFreelancer.workfreela.Entity.Profissional;
import searchFreelancer.workfreela.repository.ProfissionalRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional toRecord(Profissional profissional){
        return profissionalRepository.save(profissional);
    }
    /*

    public List<ProfissionalDTO> getAllProfissional(){
        List<Profissional> profissionals = profissionalRepository.findAll();
        return profissionals
                .stream()
                .map(x -> new ProfissionalDTO(x))
                .collect(Collectors.toList());
    }*/
}
