package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.controller.mapper.LivroMapper;
import io.github.eduardoconceicao90.libraryapi.exception.RegistroDuplicadoException;
import io.github.eduardoconceicao90.libraryapi.exception.dto.ErroResposta;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.dto.CadastroLivroDTO;
import io.github.eduardoconceicao90.libraryapi.service.LivroSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroSerivce service;
    private final LivroMapper mapper;

    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO){
        try {
            Livro livro = mapper.toEntity(livroDTO);
            service.salvar(livro);
            return ResponseEntity.ok(livro);
        } catch (RegistroDuplicadoException e) {
            var erroResposta = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

}