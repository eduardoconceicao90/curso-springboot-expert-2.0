package io.github.eduardoconceicao90.libraryapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "Campo login é obrigatório")
        String login,

        @NotBlank(message = "Campo senha é obrigatório")
        String senha,

        @Email(message = "Email inválido")
        String email,

        List<String> roles
) {
}
