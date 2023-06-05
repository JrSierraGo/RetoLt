package co.com.sofka.jpa.helper;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;

public abstract class AdapterOperations<E, D, I, R extends CrudRepository<E, I> & QueryByExampleExecutor<E>> {
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

    public Mono<D> save(D data) {
        return Mono.just(data)
                .map(this::toEntity)
                .flatMap(this::saveData)
                .map(this::toData);
    }

    public Flux<E> saveAll(List<E> entities) {
        return Flux.fromIterable(entities)
                .collectList()
                .flatMapMany(this::saveData);
    }

    protected Mono<E> saveData(E data) {
        return Mono.just(repository.save(data));
    }

    protected Flux<E> saveData(List<E> data) {
        return Flux.fromIterable(repository.saveAll(data));
    }

    public Mono<E> findById(I id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Flux<E> findAll(){
        return Flux.fromIterable(repository.findAll());
    }
}
