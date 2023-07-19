package co.com.sofka.jpa.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPACountryRepository extends CrudRepository<CountryEntity, String>, QueryByExampleExecutor<CountryEntity> {
}
