package io.github.eduardoconceicao90.libraryapi.model.dto;

import io.github.eduardoconceicao90.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(
        String nome,
        LocalDate dataNascimento,
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
