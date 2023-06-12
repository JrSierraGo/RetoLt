package co.com.sofka.jpa.sofkiano;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;



@Repository
public class JPASofkianoRepositoryAdapter extends AdapterOperations<SofkianoEntity, Sofkiano, String, JPASofkianoRepository> implements SofkianoRepository {

    protected JPASofkianoRepositoryAdapter(JPASofkianoRepository repository, ObjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Mono<SofkianoPageable> findAll(Integer page, Integer size, String sortParam) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortParam));
        return Mono.justOrEmpty(repository.findAll(pageRequest))
                .map(sofkianoEntityPage -> SofkianoPageable.builder()
                        .totalElements((int) sofkianoEntityPage.getTotalElements())
                        .totalPages(sofkianoEntityPage.getTotalPages())
                        .sofkianos(this.toData(sofkianoEntityPage.getContent()))
                        .build()
                );
    }
}
