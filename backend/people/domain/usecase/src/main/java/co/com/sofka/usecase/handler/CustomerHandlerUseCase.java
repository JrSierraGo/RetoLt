package co.com.sofka.usecase.handler;

import co.com.sofka.model.customer.Customer;
import co.com.sofka.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CustomerHandlerUseCase {

    private final CustomerRepository customerRepository;

    public Flux<Customer> getAll(){
        return customerRepository.findAll();
    }

}
