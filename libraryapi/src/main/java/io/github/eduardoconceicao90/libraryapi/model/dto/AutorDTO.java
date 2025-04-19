package io.github.eduardoconceicao90.libraryapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo nome é obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nome,

        @NotNull(message = "Campo data de nascimento é obrigatório")
        @Past(message = "Campo data de nascimento deve ser uma data passada")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo nacionalidade é obrigatório")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")
        String nacionalidade
) {
}
