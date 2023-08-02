package co.com.sofka.usecase.handler;

import co.com.sofka.model.customer.Customer;
import co.com.sofka.model.customer.gateways.CustomerRepository;
import co.com.sofka.model.documenttype.DocumentType;
import co.com.sofka.model.documenttype.gateways.DocumentTypeRepository;
import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static co.com.sofka.enums.Constants.EMPTY;

@RequiredArgsConstructor
public class SofkianoHandlerUseCase {

    private final SofkianoRepository sofkianoRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final CustomerRepository customerRepository;

    private static final String SORT_PARAM = "name";

    Logger log = Logger.getLogger(this.getClass().getName());

    public Mono<SofkianoPageable> getAll(Integer page, Integer size) {
        log.info("Enter SofkianoHandlerUseCase.getAll(page: "+ page + " size: "+ size);

        Map<String, String> documentTypeMap = new HashMap<>();

        return documentTypeRepository.findAll()
                .collectMap(DocumentType::getId, DocumentType::getAcronym)
                .doOnNext(documentTypeMap::putAll)
                .flatMap(documents -> sofkianoRepository.findAll(page, size, SORT_PARAM))
                .flatMap(sofkianoPageable -> Flux.fromIterable(sofkianoPageable.getSofkianos())
                        .flatMap(sofkiano -> customerRepository.findById(Objects.requireNonNullElse(sofkiano.getCustomerId(), EMPTY))
                                .defaultIfEmpty(Customer.builder().build())
                                .map(customer -> sofkiano.toBuilder()
                                        .documentTypeName(documentTypeMap.get(sofkiano.getDocumentTypeId()))
                                        .customerName(customer.getName()).build()
                                )
                        )
                        .collectList()
                        .map(sofkianos -> sofkianoPageable.toBuilder().sofkianos(sofkianos).build())
                );
    }
}
