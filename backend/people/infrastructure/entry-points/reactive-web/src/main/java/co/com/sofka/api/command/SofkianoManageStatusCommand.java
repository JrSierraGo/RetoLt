package co.com.sofka.api.command;

import co.com.sofka.api.dto.SofkianoDTO;
import co.com.sofka.api.dto.SofkianoStatusDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.usecase.command.SofkianoManageStatusCommandUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ms-people/sofkiano/command")
public class SofkianoManageStatusCommand extends DomainMapper<Sofkiano, SofkianoStatusDTO> {


    private final SofkianoManageStatusCommandUseCase useCase;

    protected SofkianoManageStatusCommand(ObjectMapper mapper, SofkianoManageStatusCommandUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }

    @PostMapping(
            path = "/manage-status",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Mono<SofkianoStatusDTO> changeSofkianoStatus(@RequestBody SofkianoStatusDTO sofkianoStatusDTO) {
        return useCase.process(this.dtoToDomain(sofkianoStatusDTO))
                .map(this::domainToDTO);
    }

    @Override
    protected Sofkiano dtoToDomain(SofkianoStatusDTO dtoObject) {
        return super.dtoToDomain(dtoObject).toBuilder()
                .id(dtoObject.getSofkianoId())
                .build();
    }

    @Override
    protected SofkianoStatusDTO domainToDTO(Sofkiano domainEntity) {
        return super.domainToDTO(domainEntity).toBuilder()
                .sofkianoId(domainEntity.getId())
                .build();
    }
}
