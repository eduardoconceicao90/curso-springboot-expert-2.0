package io.github.eduardoconceicao90.libraryapi.model.dto;

import io.github.eduardoconceicao90.libraryapi.model.enums.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "ISBN não pode ser vazio.")
        String isbn,

        @NotBlank(message = "Campo título é obrigatório")
        @Past(message = "Campo título deve ser uma data passada")
        String titulo,

        @NotNull(message = "Campo data de publicação é obrigatório")
        LocalDate dataPublicacao,

        GeneroLivro genero,
        BigDecimal preco,

        @NotNull(message = "Campo id do autor é obrigatório")
        UUID idAutor
) {
}
