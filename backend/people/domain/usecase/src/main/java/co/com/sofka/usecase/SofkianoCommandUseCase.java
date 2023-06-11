package co.com.sofka.usecase;

import co.com.sofka.enums.LocationStatusEnum;
import co.com.sofka.enums.PeopleExceptionEnum;
import co.com.sofka.enums.SofkianoStatusEnum;
import co.com.sofka.exception.PeopleException;
import co.com.sofka.model.location.gateways.LocationRepository;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import co.com.sofka.model.sofkianostack.gateways.SofkianoStackRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;


@RequiredArgsConstructor
public class SofkianoCommandUseCase {

    private final SofkianoRepository sofkianoRepository;
    private final LocationRepository locationRepository;
    private final SofkianoStackRepository sofkianoStackRepository;

    Logger log = Logger.getLogger(this.getClass().getName());

    private static final String EMPTY = "";

    public Mono<Sofkiano> process(Sofkiano sofkianoParam){
        log.info(":::: Start SofkianoCommandUseCase.process" + sofkianoParam);
        return Mono.justOrEmpty(sofkianoParam)
                .filter(containMinimumPayloadFields())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PeopleException(PeopleExceptionEnum.PAYLOAD_NOT_CONTAIN_MINIMUM_FIELDS))))
                .map(sofkiano -> sofkiano.toBuilder()
                        .id(Optional.ofNullable(sofkianoParam.getId()).filter(id -> !id.isBlank()).orElse(UUID.randomUUID().toString()))
                        .status(SofkianoStatusEnum.ACTIVE.name())
                        .build()
                )
                .flatMap(sofkianoRepository::save)
                .flatMap(saveSofkianoLocation(sofkianoParam))
                .flatMap(saveSofkianoStack(sofkianoParam))
                .doOnError(error -> log.severe("Error in SofkianoCommandUseCase.process " + error.getMessage()));
    }

    private Function<Sofkiano, Mono<Sofkiano>> saveSofkianoStack(Sofkiano sofkianoParam) {
        return sofkiano -> Flux.fromIterable(Optional.ofNullable(sofkianoParam.getStacks()).orElse(Collections.emptyList()))
                .map(stack -> stack.toBuilder()
                        .id(UUID.randomUUID().toString())
                        .sofkianoId(sofkiano.getId())
                        .build()
                )
                .collectList()
                .flatMapMany(sofkianoStackRepository::saveAll)
                .collectList()
                .thenReturn(sofkiano);
    }

    private Function<Sofkiano, Mono<Sofkiano>> saveSofkianoLocation(Sofkiano sofkianoParam) {
        return sofkiano -> Mono.justOrEmpty(sofkianoParam.getLocation())
                .flatMap(location -> locationRepository.findById(Optional.ofNullable(location.getId()).orElse(EMPTY)))
                .map(location -> location.toBuilder()
                        .additionalIndications(sofkianoParam.getLocation().getAdditionalIndications())
                        .build()
                )
                .defaultIfEmpty(sofkianoParam.getLocation().toBuilder()
                        .id(UUID.randomUUID().toString())
                        .sofkianoId(sofkiano.getId())
                        .status(LocationStatusEnum.CURRENT.name())
                        .build()
                )
                .concatWith(locationRepository.findByStatusAndIdNot(LocationStatusEnum.CURRENT.name(), Optional.ofNullable(sofkianoParam.getLocation().getId()).orElse(EMPTY))
                        .map(location -> location.toBuilder().status(LocationStatusEnum.PREVIOUS.name()).build())
                )
                .flatMap(locationRepository::save)
                .then(Mono.just(sofkiano));
    }

    private static Predicate<Sofkiano> containMinimumPayloadFields() {
        return sofkiano -> !Objects.requireNonNullElse(sofkiano.getDocumentNumber(), EMPTY).isBlank()
                && !Objects.requireNonNullElse(sofkiano.getDocumentTypeId(), EMPTY).isBlank()
                && !Objects.requireNonNullElse(sofkiano.getName(), EMPTY).isBlank()
                && !Objects.requireNonNullElse(sofkiano.getLastName(), EMPTY).isBlank()
                && Objects.nonNull(sofkiano.getEntryDate())
                && Objects.nonNull(sofkiano.getLocation())
                && !Objects.requireNonNullElse(sofkiano.getLocation().getCityId(), EMPTY).isBlank()
                && !Objects.requireNonNullElse(sofkiano.getLocation().getAddress(), EMPTY).isBlank();
    }


}
