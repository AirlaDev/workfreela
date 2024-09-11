package searchFreelancer.workfreela.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DetalhesNotFoundException extends RuntimeException {
    public DetalhesNotFoundException(String message) {
        super(message);
    }
}
