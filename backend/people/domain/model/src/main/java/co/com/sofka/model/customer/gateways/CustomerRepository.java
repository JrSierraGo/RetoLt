package co.com.sofka.model.customer.gateways;

import co.com.sofka.model.customer.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<Customer> findById(String id);

    Flux<Customer> findAll();
}
