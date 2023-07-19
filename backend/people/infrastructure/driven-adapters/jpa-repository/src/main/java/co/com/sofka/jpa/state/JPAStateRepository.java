package co.com.sofka.jpa.state;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAStateRepository extends CrudRepository<StateEntity, String>, QueryByExampleExecutor<StateEntity> {

    List<StateEntity> findByCountryId(String countryId);
}
