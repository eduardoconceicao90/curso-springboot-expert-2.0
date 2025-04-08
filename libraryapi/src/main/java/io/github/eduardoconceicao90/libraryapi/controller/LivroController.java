package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.controller.mapper.LivroMapper;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.dto.CadastroLivroDTO;
import io.github.eduardoconceicao90.libraryapi.service.LivroSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController implements GenericController {

    private final LivroSerivce service;
    private final LivroMapper mapper;

    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO){
        Livro livro = mapper.toEntity(livroDTO);
        service.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }

}