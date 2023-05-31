package co.com.sofka.domainconverter;

import org.reactivecommons.utils.ObjectMapper;


public abstract class DomainMapper<D, M> {

    private final ObjectMapper mapper;
    private final Class<D> domainClass;
    private final Class<M> dtoClass;

    protected DomainMapper(ObjectMapper mapper, Class<D> domainClass, Class<M> dtoClass) {
        this.mapper = mapper;
        this.domainClass = domainClass;
        this.dtoClass = dtoClass;
    }


    protected M domainToDTO(D domainEntity){
        return this.mapper.map(domainEntity, this.dtoClass);
    }

    protected D dtoToDomain(M dtoObject){
        return this.mapper.map(dtoObject, this.domainClass);
    }
}
