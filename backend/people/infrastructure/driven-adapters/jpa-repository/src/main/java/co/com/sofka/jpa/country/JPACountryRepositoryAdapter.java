package co.com.sofka.jpa.country;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPACountryRepositoryAdapter extends AdapterOperations<CountryEntity, Country, String, JPACountryRepository> implements CountryRepository {

    protected JPACountryRepositoryAdapter(JPACountryRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }
}
