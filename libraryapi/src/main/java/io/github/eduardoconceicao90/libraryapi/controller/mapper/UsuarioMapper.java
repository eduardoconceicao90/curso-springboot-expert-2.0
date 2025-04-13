package io.github.eduardoconceicao90.libraryapi.controller.mapper;

import io.github.eduardoconceicao90.libraryapi.model.Usuario;
import io.github.eduardoconceicao90.libraryapi.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
