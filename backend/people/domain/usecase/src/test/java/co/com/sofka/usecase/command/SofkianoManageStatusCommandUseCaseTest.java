package co.com.sofka.usecase.command;

import co.com.sofka.enums.SofkianoStatusEnum;
import co.com.sofka.exception.PeopleException;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SofkianoManageStatusCommandUseCaseTest {

    @InjectMocks
    private SofkianoManageStatusCommandUseCase sofkianoManageStatusCommandUseCase;

    @Mock
    private SofkianoRepository sofkianoRepository;

    @Test
    void whenSofkianoNotContainData_ShouldReturnError() {
        Sofkiano sofkiano = Sofkiano.builder().build();

        Mono<Sofkiano> publisher =  sofkianoManageStatusCommandUseCase.process(sofkiano);

        StepVerifier.create(publisher)
                .expectError(PeopleException.class)
                .verify();
    }

    @Test
    void whenSofkianoInactiveAndDataIsValid_ShouldSaveAndReturn(){
        Sofkiano sofkiano = Sofkiano.builder()
                .id(UUID.randomUUID().toString())
                .status(SofkianoStatusEnum.INACTIVE.name())
                .build();

        when(sofkianoRepository.findById(anyString())).thenReturn(Mono.just(sofkiano));
        when(sofkianoRepository.save(any())).thenReturn(Mono.just(sofkiano));

        Mono<Sofkiano> publisher = sofkianoManageStatusCommandUseCase.process(sofkiano);

        StepVerifier.create(publisher)
                .expectNext(sofkiano)
                .verifyComplete();

        verify(sofkianoRepository).save(any());
    }

    @Test
    void whenSofkianoActiveAndDataIsValid_ShouldSaveAndReturn(){
        Sofkiano sofkiano = Sofkiano.builder()
                .id(UUID.randomUUID().toString())
                .status(SofkianoStatusEnum.INACTIVE.name())
                .entryDate(0L)
                .build();

        when(sofkianoRepository.findById(anyString())).thenReturn(Mono.just(sofkiano));
        when(sofkianoRepository.save(any())).thenReturn(Mono.just(sofkiano));

        Mono<Sofkiano> publisher = sofkianoManageStatusCommandUseCase.process(sofkiano);

        StepVerifier.create(publisher)
                .expectNext(sofkiano)
                .verifyComplete();

        verify(sofkianoRepository).save(any());
    }
}