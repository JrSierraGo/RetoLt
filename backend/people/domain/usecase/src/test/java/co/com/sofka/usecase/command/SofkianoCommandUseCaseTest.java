package co.com.sofka.usecase.command;

import co.com.sofka.exception.PeopleException;
import co.com.sofka.model.location.Location;
import co.com.sofka.model.location.gateways.LocationRepository;
import co.com.sofka.model.mq.gateways.RabbitMQService;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import co.com.sofka.model.sofkianostack.Stack;
import co.com.sofka.model.sofkianostack.gateways.SofkianoStackRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SofkianoCommandUseCaseTest {

    @InjectMocks
    private SofkianoCommandUseCase sofkianoCommandUseCase;

    @Mock
    private SofkianoRepository sofkianoRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private SofkianoStackRepository sofkianoStackRepository;
    @Mock
    private  RabbitMQService<Sofkiano> rabbitMQService;



    @Test
    void whenSofkianoNotContainData_ShouldReturnError() {
        Sofkiano sofkiano = Sofkiano.builder().build();

        Mono<Sofkiano> publisher =  sofkianoCommandUseCase.process(sofkiano);

        StepVerifier.create(publisher)
                .expectError(PeopleException.class)
                .verify();
    }

    @Test
    void whenSofkianoIsValid_ShouldSaveAndReturn() {
        String sofkianoId = UUID.randomUUID().toString();
        Sofkiano sofkiano = buildSofkiano();

        when(sofkianoRepository.save(any())).thenReturn(Mono.just(buildSofkiano().toBuilder().id(sofkianoId).build()));
        when(locationRepository.save(any())).thenReturn(Mono.just(buildLocation()));
        when(locationRepository.findByStatusAndIdNot(anyString(), anyString())).thenReturn(Mono.just(buildLocation()));
        when(locationRepository.findById(anyString())).thenReturn(Mono.just(buildLocation()));
        when(sofkianoStackRepository.saveAll(any())).thenReturn(Flux.just(Stack.builder().build()));
        when(rabbitMQService.sendMessage(any())).thenReturn(Mono.empty());

        Mono<Sofkiano> publisher =  sofkianoCommandUseCase.process(sofkiano);

        StepVerifier.create(publisher)
                .expectNextMatches(response -> response.getId().equals(sofkianoId))
                .verifyComplete();
    }

    Sofkiano buildSofkiano(){
        return  Sofkiano.builder()
                .documentTypeId(UUID.randomUUID().toString())
                .documentNumber("102454122")
                .name("Francisco")
                .lastName("Ramirez")
                .entryDate(0L)
                .location(buildLocation())
                .stacks(List.of(Stack.builder().build()))
                .build();
    }

    Location buildLocation(){
        return Location.builder()
                .cityId(UUID.randomUUID().toString())
                .address(RandomString.make())
                .build();
    }
}