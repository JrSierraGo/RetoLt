package co.com.sofka.api.exception;

import co.com.sofka.api.dto.ErrorHandlerDTO;
import co.com.sofka.enums.PeopleExceptionEnum;
import co.com.sofka.exception.PeopleException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(PeopleException.class)
    Mono<ResponseEntity<ErrorHandlerDTO>> postPeopleException(PeopleException ex) {
        log.error("handling exception::" + Arrays.toString(ex.getStackTrace()));
        return Mono.just(ResponseEntity
                .internalServerError()
                .body(ErrorHandlerDTO.builder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                )
        );
    }


    @ExceptionHandler(PSQLException.class)
    Mono<ResponseEntity<ErrorHandlerDTO>> postPsqlException(PSQLException ex) {
        log.error("handling exception::" + Arrays.toString(ex.getStackTrace()));
        return Mono.just(ResponseEntity
                .badRequest()
                .body(ErrorHandlerDTO.builder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(PeopleExceptionEnum.ERROR_DATABASE_OPERATION.name())
                        .build()
                )
        );
    }


}
