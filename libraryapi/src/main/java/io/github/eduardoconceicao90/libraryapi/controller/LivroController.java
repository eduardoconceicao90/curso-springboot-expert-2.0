package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.controller.mapper.LivroMapper;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.dto.CadastroLivroDTO;
import io.github.eduardoconceicao90.libraryapi.model.dto.ResultadoPesquisaLivroDTO;
import io.github.eduardoconceicao90.libraryapi.service.LivroSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController implements GenericController {

    private final LivroSerivce service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO) {
        Livro livro = mapper.toEntity(livroDTO);
        service.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
            ResultadoPesquisaLivroDTO livroDTO = mapper.toDTO(livro);
            return ResponseEntity.ok(livroDTO);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Livro> livroOptional = service.obterPorId(UUID.fromString(id));

        if (livroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(livroOptional.get());
        return ResponseEntity.noContent().build();
    }

}