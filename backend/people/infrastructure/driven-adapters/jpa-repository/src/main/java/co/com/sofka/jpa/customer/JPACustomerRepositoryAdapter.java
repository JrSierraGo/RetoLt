package co.com.sofka.jpa.customer;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.customer.Customer;
import co.com.sofka.model.customer.gateways.CustomerRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPACustomerRepositoryAdapter extends AdapterOperations<CustomerEntity, Customer, String, JPACustomerRepository> implements CustomerRepository {

    protected JPACustomerRepositoryAdapter(JPACustomerRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }
}
