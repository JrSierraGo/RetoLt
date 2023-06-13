package co.com.sofka.usecase.command;

import co.com.sofka.enums.PeopleExceptionEnum;
import co.com.sofka.enums.SofkianoStatusEnum;
import co.com.sofka.exception.PeopleException;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import static co.com.sofka.enums.Constants.EMPTY;

@RequiredArgsConstructor
public class SofkianoManageStatusCommandUseCase {

    private final SofkianoRepository sofkianoRepository;

    public Mono<Sofkiano> process(Sofkiano sofkianoParam) {
        return Mono.justOrEmpty(sofkianoParam)
                .filter(containMinimumPayloadFields())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PeopleException(PeopleExceptionEnum.PAYLOAD_NOT_CONTAIN_MINIMUM_FIELDS))))
                .flatMap(sofkiano -> sofkianoRepository.findById(sofkiano.getId())
                        .map(sofkianoFound -> sofkianoFound.toBuilder()
                                .entryDate(sofkiano.getEntryDate())
                                .status(sofkiano.getStatus())
                                .build()
                        )
                )
                .flatMap(sofkianoRepository::save);
    }


    private static Predicate<Sofkiano> containMinimumPayloadFields() {
        return sofkiano -> !Objects.requireNonNullElse(sofkiano.getId(), EMPTY).isBlank()
                && Arrays.stream(SofkianoStatusEnum.values()).anyMatch(sofkianoStatusEnum -> sofkianoStatusEnum.name().equals(sofkiano.getStatus()))
                && (SofkianoStatusEnum.INACTIVE.name().equals(sofkiano.getStatus()) || Objects.nonNull(sofkiano.getEntryDate()));

    }
}
