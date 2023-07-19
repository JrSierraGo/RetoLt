package co.com.sofka.api.handler;

import co.com.sofka.api.dto.CustomerDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.customer.Customer;
import co.com.sofka.usecase.handler.CustomerHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/ms-people/customer/query")
public class CustomerQueryHandler extends DomainMapper<Customer, CustomerDTO> {

    private final CustomerHandlerUseCase useCase;

    protected CustomerQueryHandler(ObjectMapper mapper, CustomerHandlerUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @GetMapping(
            path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Flux<CustomerDTO> getAll() {
        return useCase.getAll()
                .map(this::domainToDTO);
    }
}
