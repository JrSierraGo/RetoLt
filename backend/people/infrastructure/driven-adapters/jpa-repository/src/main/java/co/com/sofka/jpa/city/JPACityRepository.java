package co.com.sofka.jpa.city;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPACityRepository extends CrudRepository<CityEntity, String>, QueryByExampleExecutor<CityEntity> {

    List<CityEntity> findByStateId(String stateId);
}
