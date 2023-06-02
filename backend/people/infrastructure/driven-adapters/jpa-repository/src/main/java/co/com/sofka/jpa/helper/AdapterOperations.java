package co.com.sofka.jpa.helper;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

public abstract class AdapterOperations<E, D, I, R extends CrudRepository<D, I> & QueryByExampleExecutor<D>> {
    protected R repository;
    private Class<D> dataClass;
    protected ObjectMapper mapper;
    private Function<D, E> toEntityFn;

    @SuppressWarnings("unchecked")
    protected AdapterOperations(R repository, ObjectMapper mapper, Function<D, E> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
        this.toEntityFn = toEntityFn;
    }

    protected D toData(E entity) {
        return mapper.map(entity, dataClass);
    }

    protected E toEntity(D data) {
        return data != null ? toEntityFn.apply(data) : null;
    }

    public Mono<E> save(E entity) {
        return Mono.just(entity)
                .map(this::toData)
                .flatMap(this::saveData)
                .map(this::toEntity);
    }

    public Flux<E> saveAll(List<E> entities) {
        return Flux.fromIterable(entities)
                .map(this::toData)
                .collect(Collectors.toList())
                .flatMapMany(this::saveData)
                .map(this::toEntity);
    }

    protected Mono<D> saveData(D data) {
        return Mono.just(repository.save(data));
    }

    protected Flux<D> saveData(List<D> data) {
        return Flux.fromIterable(repository.saveAll(data));
    }

    public Mono<E> findById(I id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    public Flux<E> findAll(){
        return repository.findAll()
                .map(this::toEntity);
    }
}
