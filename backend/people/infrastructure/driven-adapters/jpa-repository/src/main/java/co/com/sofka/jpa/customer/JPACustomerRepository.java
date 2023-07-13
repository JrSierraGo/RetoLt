package co.com.sofka.jpa.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPACustomerRepository extends CrudRepository<CustomerEntity, String>, QueryByExampleExecutor<CustomerEntity> {
}
