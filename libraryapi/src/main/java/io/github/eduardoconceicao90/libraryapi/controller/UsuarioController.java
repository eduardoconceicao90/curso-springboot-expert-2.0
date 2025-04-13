package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.controller.mapper.UsuarioMapper;
import io.github.eduardoconceicao90.libraryapi.model.dto.UsuarioDTO;
import io.github.eduardoconceicao90.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void salvar(@RequestBody UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }

}
