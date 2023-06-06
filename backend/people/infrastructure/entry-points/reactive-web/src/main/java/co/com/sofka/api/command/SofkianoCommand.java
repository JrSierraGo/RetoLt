package co.com.sofka.api.command;

import co.com.sofka.api.dto.SofkianoDTO;
import co.com.sofka.domainconverter.DomainMapper;
import co.com.sofka.model.sofkiano.Skill;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.usecase.SofkianoCommandUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ms-people/sofkiano/command")
public class SofkianoCommand extends DomainMapper<Sofkiano, SofkianoDTO> {

    private final SofkianoCommandUseCase useCase;

    protected SofkianoCommand(ObjectMapper mapper, SofkianoCommandUseCase useCase) {
        super(mapper);
        this.useCase = useCase;
    }


    @PostMapping(
            path = "/save",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Mono<SofkianoDTO> saveSofkiano(SofkianoDTO sofkianoDTO) {
        return useCase.process(this.dtoToDomain(sofkianoDTO))
                .map(this::domainToDTO);
    }

    @Override
    protected Sofkiano dtoToDomain(SofkianoDTO dtoObject) {
        return super.dtoToDomain(dtoObject)
                .toBuilder()
                .skills(this.mapListObject(dtoObject.getSkills(), Skill.class))
                .build();
    }
}
