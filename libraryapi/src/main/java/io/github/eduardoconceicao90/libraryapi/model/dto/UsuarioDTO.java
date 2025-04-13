package io.github.eduardoconceicao90.libraryapi.model.dto;

import java.util.List;

public record UsuarioDTO(
        String login,
        String senha,
        List<String> roles
) {
}
