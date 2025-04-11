package io.github.eduardoconceicao90.libraryapi.exception.common;

import io.github.eduardoconceicao90.libraryapi.exception.CampoInvalidoException;
import io.github.eduardoconceicao90.libraryapi.exception.OperacaoNaoPermitidaException;
import io.github.eduardoconceicao90.libraryapi.exception.RegistroDuplicadoException;
import io.github.eduardoconceicao90.libraryapi.exception.dto.ErroCampo;
import io.github.eduardoconceicao90.libraryapi.exception.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErroCampo> listaDeErros = fieldErrors
                            .stream()
                            .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                            .collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação.", listaDeErros);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException ex) {
        return ErroResposta.conflito(ex.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException ex) {
        return ErroResposta.respostaPadrao(ex.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCampoInvalidoException(CampoInvalidoException ex) {
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.",
                List.of(new ErroCampo(ex.getCampo(), ex.getMessage()))
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratadosException(OperacaoNaoPermitidaException ex) {
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro inesperado! Entre em contato com desenvolvedor.", List.of());
    }

}
