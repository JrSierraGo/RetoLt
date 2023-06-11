package co.com.sofka.domainconverter;

import org.reactivecommons.utils.ObjectMapper;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public abstract class DomainMapper<D, T> {

    private final ObjectMapper mapper;
    protected final Class<D> domainClass;
    protected final Class<T> dtoClass;

    @SuppressWarnings("unchecked")
    protected DomainMapper(ObjectMapper mapper) {
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.domainClass = (Class<D>) genericSuperclass.getActualTypeArguments()[0];
        this.dtoClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }


    protected T domainToDTO(D domainEntity){
        return this.mapper.map(domainEntity, this.dtoClass);
    }

    protected D dtoToDomain(T dtoObject){
        return this.mapper.map(dtoObject, this.domainClass);
    }
    protected <O, S> List<S> mapListObject(Collection<O> object, Class<S> classMap){
        return Optional.ofNullable(object).orElse(Collections.emptyList()).stream()
                .map(objectToMapper ->  this.mapper.map(objectToMapper, classMap))
                .collect(Collectors.toList());
    }

    protected <O, S> S mapObject(O object, Class<S> classMap){
        return this.mapper.map(object, classMap);
    }
}
