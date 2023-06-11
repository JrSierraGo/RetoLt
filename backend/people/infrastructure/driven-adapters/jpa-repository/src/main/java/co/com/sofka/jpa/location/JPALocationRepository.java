package co.com.sofka.jpa.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface JPALocationRepository extends CrudRepository<LocationEntity, String>, QueryByExampleExecutor<LocationEntity> {

    Optional<LocationEntity> findByStatusAndIdNot(String status, String id);
}
