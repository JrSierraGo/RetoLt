package co.com.sofka.usecase.handler;

import co.com.sofka.model.customer.Customer;
import co.com.sofka.model.customer.gateways.CustomerRepository;
import co.com.sofka.model.documenttype.DocumentType;
import co.com.sofka.model.documenttype.gateways.DocumentTypeRepository;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.SofkianoPageable;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import co.com.sofka.model.sofkianostack.Stack;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SofkianoHandlerUseCaseTest {

    @InjectMocks
    private SofkianoHandlerUseCase sofkianoHandlerUseCase;
    @Mock
    private SofkianoRepository sofkianoRepository;
    @Mock
    private DocumentTypeRepository documentTypeRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void whenRequestPageAndSizeWithData_ShouldReturnSofkianosList() {
        Integer page = 0;
        Integer size = 10;

        when(documentTypeRepository.findAll()).thenReturn(Flux.just(buildDocumentType("1"), buildDocumentType("2")));
        when(sofkianoRepository.findAll(anyInt(), anyInt(), anyString())).thenReturn(Mono.just(buildSofkianoPageable()));
        when(customerRepository.findById(anyString())).thenReturn(Mono.just(Customer.builder().name(RandomString.make()).build()));

        Mono<SofkianoPageable> publisher = sofkianoHandlerUseCase.getAll(page, size);

        StepVerifier.create(publisher)
                .expectNextMatches(response -> response.getSofkianos().size() > 1)
                .verifyComplete();
    }

    SofkianoPageable buildSofkianoPageable() {
        return SofkianoPageable.builder()
                .totalElements(2)
                .totalPages(1)
                .sofkianos(List.of(buildSofkiano("1", null), buildSofkiano("2", "0011")))
                .build();
    }

    Sofkiano buildSofkiano(String documentType, String customerId){
        return  Sofkiano.builder()
                .documentTypeId(documentType)
                .documentNumber("102454122")
                .name("Francisco")
                .lastName("Ramirez")
                .customerId(customerId)
                .entryDate(0L)
                .stacks(List.of(Stack.builder().build()))
                .build();
    }


    DocumentType buildDocumentType(String id){
        return DocumentType.builder()
                .id(id)
                .name(RandomString.make(5))
                .acronym(RandomString.make(2))
                .build();
    }

}