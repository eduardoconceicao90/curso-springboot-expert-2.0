package io.github.eduardoconceicao90.libraryapi.model.dto;

import io.github.eduardoconceicao90.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo nome é obrigatório")
        String nome,

        @NotNull(message = "Campo data de nascimento é obrigatório")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo nacionalidade é obrigatório")
        String nacionalidade
) {

    public Autor mapearParaAutor() {
        return Autor.builder()
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .nacionalidade(this.nacionalidade)
                .build();
    }
}
